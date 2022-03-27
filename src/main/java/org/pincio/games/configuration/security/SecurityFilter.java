package org.pincio.games.configuration.security;

import org.pincio.games.configuration.security.model.Credentials;
import org.pincio.games.configuration.security.model.SecurityProperties;
import org.pincio.games.model.Person;
import org.pincio.games.service.CookieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    Logger log = LoggerFactory.getLogger(SecurityFilter.class.getName());

    @Autowired
    SecurityService securityService;

    @Autowired
    SecurityProperties restSecProps;

    @Autowired
    CookieService cookieUtils;

    @Autowired
    SecurityProperties securityProps;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        verifyToken(request);
        filterChain.doFilter(request, response);
    }

    private void verifyToken(HttpServletRequest request) {
        String session = null;
        FirebaseToken decodedToken = null;
        Credentials.CredentialType type = null;
        boolean strictServerSessionEnabled = securityProps.getFirebaseProps().isEnableStrictServerSession();
        Cookie sessionCookie = cookieUtils.getCookie("session");
        String token = securityService.getBearerToken(request);
        logger.info(token);
        try {
            if (sessionCookie != null) {
                session = sessionCookie.getValue();
                decodedToken = FirebaseAuth.getInstance().verifySessionCookie(session,
                        securityProps.getFirebaseProps().isEnableCheckSessionRevoked());
                type = Credentials.CredentialType.SESSION;
            } else if (!strictServerSessionEnabled) {
                if (token != null && !token.equalsIgnoreCase("undefined")) {
                    decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
                    type = Credentials.CredentialType.ID_TOKEN;
                }
            }
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            log.error("Firebase Exception:: ", e.getLocalizedMessage());
        }
        Person user = firebaseTokenToUserDto(decodedToken);
        if (user != null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
                    new Credentials(type, decodedToken, token, session), null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private Person firebaseTokenToUserDto(FirebaseToken decodedToken) {
        Person user = null;
        if (decodedToken != null) {
            user = new Person();
            user.setName(decodedToken.getName());
            user.setEmail(decodedToken.getEmail());
            //user.setPicture(decodedToken.getPicture());
            //user.setIssuer(decodedToken.getIssuer());
            user.setValid(decodedToken.isEmailVerified());
        }
        return user;
    }

}
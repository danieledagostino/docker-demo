package org.pincio.games.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.pincio.games.dto.MyUserPrincipal;
import org.pincio.games.dto.UserDto;
import org.pincio.games.model.User;
import org.pincio.games.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new MyUserPrincipal(user);
    }

    public Long insertNewUser(UserDto user) throws Exception {

        if (!user.getPassword().equals(user.getMatchingPassword())) {
            throw new Exception("password not match");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getFirstName());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setSurname(user.getLastName());
        newUser.setRole("USER");
        //newUser.setValid(false); TODO to uncomment in prod
        newUser.setValid(true);
        newUser.setPhotoId(user.getProfileImage());

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()[{]};:";
        String token = RandomStringUtils.random( 15, characters );

        newUser.setToken(token);

        newUser = userRepository.save(newUser);

        return newUser.getId();
        //sendEmail
    }

    public void emailConfirmation(String token) {
        User user = userRepository.findByToken(token);

        if (user != null) {
            user.setToken("");
            user.setValid(true);
            userRepository.save(user);
        }
    }

    public String changePassword(UserDto user) {

        return null;
    }

    public void askNewPassword(String email) {

    }

    public String changeImageProfile(byte[] file) {

        return null;
    }

}

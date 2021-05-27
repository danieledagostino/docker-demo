package org.pincio.games.service;

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
public class PincioUserDetailsService  implements UserDetailsService {

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

    public String insertNewUser(UserDto user) {

        if (!user.getPassword().equals(user.getMatchingPassword())) {
            return "Password not match";
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getFirstName());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setSurname(user.getLastName());
        newUser.setRole("USER");
        newUser.setValid(false);

        try {
            userRepository.save(newUser);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "OK";
    }
}

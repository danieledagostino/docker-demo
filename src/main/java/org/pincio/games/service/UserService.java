package org.pincio.games.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.pincio.games.dto.MyUserPrincipal;
import org.pincio.games.dto.PersonDto;
import org.pincio.games.dto.UserDto;
import org.pincio.games.model.Person;
import org.pincio.games.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Person person = userRepository.findByEmail(email);
        if (person == null) {
            throw new UsernameNotFoundException(email);
        }
        return new MyUserPrincipal(person);
    }

    public Long insertNewUser(UserDto user) throws Exception {

        if (!user.getPassword().equals(user.getMatchingPassword())) {
            throw new Exception("password not match");
        }

        Person newPerson = new Person();
        newPerson.setEmail(user.getEmail());
        newPerson.setName(user.getFirstName());
        newPerson.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newPerson.setSurname(user.getLastName());
        newPerson.setRole("USER");
        //newUser.setValid(false); TODO to uncomment in prod
        newPerson.setValid(true);
        newPerson.setPhotoId(user.getProfileImage());

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()[{]};:";
        String token = RandomStringUtils.random( 15, characters );

        newPerson.setToken(token);

        newPerson = userRepository.save(newPerson);

        return newPerson.getId();
        //sendEmail
    }

    public boolean emailConfirmation(String token) {
        Person person = userRepository.findByToken(token);

        if (person != null) {
            person.setToken("");
            person.setValid(true);
            userRepository.save(person);

            return true;
        } else {
            return false;
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

    public PersonDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal)authentication.getPrincipal();

        Person person = myUserPrincipal.getPerson();

        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setEmail(person.getEmail());
        dto.setName(person.getName());
        dto.setSurname(person.getSurname());
        dto.setPhotoId(person.getPhotoId());

        return dto;
    }

}

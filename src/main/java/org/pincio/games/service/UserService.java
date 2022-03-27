package org.pincio.games.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.apache.commons.lang3.RandomStringUtils;
import org.pincio.games.dto.MyUserPrincipal;
import org.pincio.games.dto.PagePersonDto;
import org.pincio.games.dto.PersonDto;
import org.pincio.games.dto.UserDto;
import org.pincio.games.model.Person;
import org.pincio.games.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        UserRecord.CreateRequest firebaseUser = new UserRecord.CreateRequest();
        firebaseUser.setEmail(user.getEmail());
        firebaseUser.setPassword(user.getPassword());
        UserRecord userRecord = FirebaseAuth.getInstance().createUser(firebaseUser);

        Person newPerson = new Person(userRecord.getUid());
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

    public byte[] findUserImageProfile(Long userId) {
        Person person = userRepository.findById(userId).get();

        return person.getPhotoId();
    }

    public List<PersonDto> getAllUsersByRaceType(Long raceType) {

        //userRepository.findAllByRaceType();

        return null;
    }

    public PagePersonDto getAllFreeUsers(int page, int size) {
        Page<Person> pages = userRepository.findByTeamsIsEmpty(PageRequest.of(page, size));

        PagePersonDto pagePersonDto = new PagePersonDto();

        pagePersonDto.setTotalPages(pages.getTotalPages());
        pagePersonDto.setTotalElements(pages.getNumberOfElements());

        List<PersonDto> list = null;

        list = pages.get().map( p -> new PersonDto(p.getId(), p.getName())).collect(Collectors.toList());
        pagePersonDto.setPersonDtoList(list);

        return pagePersonDto;
    }
}

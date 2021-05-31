package org.pincio.games.controller;

import org.pincio.games.dto.MyUserPrincipal;
import org.pincio.games.dto.PersonDto;
import org.pincio.games.model.Person;
import org.pincio.games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @RolesAllowed("USER")
    @PostMapping(value = "/uploadImageProfile", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<String> uploadImageProfile(@RequestParam("file") MultipartFile file) {
        String response = null;
        try {
            response = userService.changeImageProfile(file.getBytes());
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @RolesAllowed("USER")
    @PostMapping(value = "/passwordChange", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> passwordChange(@RequestParam("file") MultipartFile file) {

        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @RolesAllowed("USER")
    @GetMapping(value = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PersonDto> home() {

        PersonDto dto = userService.getCurrentUser();

        return new ResponseEntity<PersonDto>(dto, HttpStatus.OK);
    }
}

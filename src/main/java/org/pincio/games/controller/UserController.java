package org.pincio.games.controller;

import org.pincio.games.dto.MyUserPrincipal;
import org.pincio.games.dto.PagePersonDto;
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
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.List;

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
        dto.setPhotoId(null);
        dto.setPhotoUrl("/profileImage");
        return new ResponseEntity<PersonDto>(dto, HttpStatus.OK);
    }

    @RolesAllowed("USER")
    @GetMapping(value = "/profileImage")
    public @ResponseBody byte[] getProfileImage() {
        PersonDto dto = userService.getCurrentUser();
        return dto.getPhotoId();
    }

    @RolesAllowed("USER")
    @GetMapping(value = "/profileImage/{userId}")
    public @ResponseBody byte[] getUserProfileImage(@PathVariable("userId") Long userId) {
        byte[] photoId = userService.findUserImageProfile(userId);
        return photoId;
    }

    @RolesAllowed("USER")
    @GetMapping(value = "/allFreeUsers", params = { "page", "size" })
    public ResponseEntity<PagePersonDto> allFreeUsers(@RequestParam("page") int page,
                                                        @RequestParam("size") int size, UriComponentsBuilder uriBuilder) {
        PagePersonDto dto = userService.getAllFreeUsers(page, size);
        return new ResponseEntity<PagePersonDto>(dto, HttpStatus.OK);
    }
}
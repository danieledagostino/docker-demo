package org.pincio.games.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @PostMapping(value = "/uploadImageProfile", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> uploadImageProfile(@RequestParam("file") MultipartFile file) {

        return new ResponseEntity<String>("", HttpStatus.OK);
    }
}

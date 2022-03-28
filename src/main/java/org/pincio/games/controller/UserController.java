package org.pincio.games.controller;

import org.pincio.games.dto.*;
import org.pincio.games.model.Person;
import org.pincio.games.service.TeamOrganizationService;
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

    @Autowired
    TeamOrganizationService teamService;

    ////@RolesAllowed("USER")
    @GetMapping(value = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<HomeDto> home() {

        HomeDto dto = new HomeDto();
        dto.setPersonDto(userService.getCurrentUser());
        dto.setTeams(teamService.loadAllTeamsByCountry());
        return new ResponseEntity<HomeDto>(dto, HttpStatus.OK);
    }

    //@RolesAllowed("USER")
    @GetMapping(value = "/allFreeUsers", params = { "page", "size" })
    public ResponseEntity<PagePersonDto> allFreeUsers(@RequestParam("page") int page,
                                                        @RequestParam("size") int size, UriComponentsBuilder uriBuilder) {
        PagePersonDto dto = userService.getAllFreeUsers(page, size);
        return new ResponseEntity<PagePersonDto>(dto, HttpStatus.OK);
    }
}
package org.pincio.games.controller;

import org.pincio.games.dto.TeamDto;
import org.pincio.games.dto.UserDto;
import org.pincio.games.service.TeamOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    @Autowired
    TeamOrganizationService service;

    @RolesAllowed("MOD")
    @GetMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> check() {

        return new ResponseEntity<String>("All works", HttpStatus.OK);
    }

    @RolesAllowed("MOD")
    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<TeamDto>> findAll() {

        return new ResponseEntity<List<TeamDto>>(
                service.loadAllTeams(), HttpStatus.OK);
    }

    @RolesAllowed("USER")
    @GetMapping(value = "/findAll/{raceType}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<TeamDto>> findAllByRaceType(@PathVariable("raceType") Long raceType) {

        return new ResponseEntity<List<TeamDto>>(
                service.loadAllTeamsByRaceType(raceType), HttpStatus.OK);
    }

    @RolesAllowed("USER")
    @PostMapping(value = "/join", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> join(@RequestBody TeamDto dto) {

        String response = service.joinTeam(dto);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}

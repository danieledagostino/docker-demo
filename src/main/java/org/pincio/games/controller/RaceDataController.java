package org.pincio.games.controller;

import org.pincio.games.dto.RaceDataDto;
import org.pincio.games.dto.TeamDto;
import org.pincio.games.dto.UserDto;
import org.pincio.games.service.ClassificationService;
import org.pincio.games.service.PincioUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/race")
public class RaceDataController {

    @Autowired
    ClassificationService classificationService;

    @GetMapping(value = "/classificationByRaceType/{raceType}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<TeamDto>> classificationByRaceType(@PathVariable("raceType") Integer raceType) {

        return new ResponseEntity<List<TeamDto>>(
                classificationService.getLastByRace(raceType), HttpStatus.OK);
    }

    @PostMapping(value = "/insertData", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> insertData(@RequestBody RaceDataDto dto) {

        String response = classificationService.insertData(dto);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

}


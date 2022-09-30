package org.pincio.games.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pincio.games.dto.RaceDataDto;
import org.pincio.games.dto.TeamDto;
import org.pincio.games.dto.UserDto;
import org.pincio.games.model.RaceType;
import org.pincio.games.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/race")
public class RaceDataController {

    @Autowired
    ClassificationService classificationService;

    //@RolesAllowed("USER")
    @CrossOrigin
    @GetMapping(value = "/classificationByType/{raceType}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<TeamDto>> classificationByRaceType(@PathVariable("raceType") Integer raceType) {

        return new ResponseEntity<List<TeamDto>>(
                classificationService.getLastByRace(raceType), HttpStatus.OK);
    }

    //@RolesAllowed("USER")
    @PostMapping(value = "/insertData", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public ResponseEntity<String> insertData(@RequestParam("startPhoto") MultipartFile startPhoto,
                                             @RequestParam("endPhoto") MultipartFile endPhoto,
                                             @RequestPart("raceData") String json) {

        String response = null;
        try {
            RaceDataDto dto = new ObjectMapper().readValue(json, RaceDataDto.class);
            dto.setStartPhotogroup(startPhoto.getBytes());
            dto.setEndphotogroup(endPhoto.getBytes());
            response = classificationService.insertData(dto);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    //@RolesAllowed("USER")
    @GetMapping(value = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<RaceType>> raceTypes() {

        List<RaceType> list = classificationService.getAllRaceTypes();

        return new ResponseEntity<List<RaceType>>(list, HttpStatus.OK);
    }

}


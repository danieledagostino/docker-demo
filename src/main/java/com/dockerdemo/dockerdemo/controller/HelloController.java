package com.dockerdemo.dockerdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class HelloController {

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> mainPage() {

		return new ResponseEntity<>("All work", HttpStatus.OK);
	}

	@GetMapping(value = "/hi/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public ResponseEntity<String> hi(@PathVariable("name") String name) {
		
        return new ResponseEntity<>("Hi "+name+"!!", HttpStatus.OK);
	}

}

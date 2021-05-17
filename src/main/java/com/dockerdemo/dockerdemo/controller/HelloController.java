package com.dockerdemo.dockerdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("")
public class HelloController {

	@Value("${host}")
	String host;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> mainPage() {

		return new ResponseEntity<>("All work version 2", HttpStatus.OK);
	}

	@GetMapping(value = "/hi/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public ResponseEntity<String> hi(@PathVariable("name") String name) {
		
        return new ResponseEntity<>("Hi "+name+"!!", HttpStatus.OK);
	}

	@PostMapping(value = "/gm", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> gm(@RequestBody String name) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(name, headers);

		ResponseEntity<String> response = restTemplate.postForEntity("http://"+host+":9090/service/gm", request, String.class);
		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> login(@RequestBody String name) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(name, headers);

		return new ResponseEntity<>("Autenticazione riuscita", HttpStatus.OK);
	}

}

package org.pincio.games.controller;

import org.pincio.games.dto.UserDto;
import org.pincio.games.service.PincioUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/public")
public class LoginController {

	@Autowired
	PincioUserDetailsService userService;

	@GetMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> check() {

		return new ResponseEntity<>("All works", HttpStatus.OK);
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

	@PostMapping(value = "/subscribe", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> subscribe(@RequestBody UserDto dto) {

		String response = userService.insertNewUser(dto);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}



}

package org.pincio.games.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.pincio.games.dto.UserDto;
import org.pincio.games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/api/public")
public class LoginController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> check() {

		return new ResponseEntity<String>("All works", HttpStatus.OK);
	}

	@PostMapping(value = "/subscribe", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	@ResponseBody
	public ResponseEntity<String> subscribe(@RequestParam("file") MultipartFile file, @RequestPart("user") String json) {

		String response = "";
		try {
			UserDto dto = new ObjectMapper().readValue(json, UserDto.class);
			dto.setProfileImage(file.getBytes());
			userService.insertNewUser(dto);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Subscribed", HttpStatus.OK);
	}

	@GetMapping(value = "/newPassword", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> newPassword(@RequestBody String email) {

		return new ResponseEntity<String>("", HttpStatus.OK);
	}

	@GetMapping(value = "/tokenValidation/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> tokenValidation(@PathVariable("token") String token) {

		Boolean response = userService.emailConfirmation(token);

		if (response) {
			return new ResponseEntity<String>("Email confirmed. Get back to the app.", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Email not confirmed. Please check the url you received", HttpStatus.OK);
		}
	}
}

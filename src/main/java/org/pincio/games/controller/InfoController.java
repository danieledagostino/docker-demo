package org.pincio.games.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class InfoController {

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> mainPage() {

		return new ResponseEntity<String>("All work version 2", HttpStatus.OK);
	}

	@GetMapping(value = "/hi/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public ResponseEntity<String> hi(@PathVariable("name") String name) {
		
        return new ResponseEntity<String>("Hi "+name+"!!", HttpStatus.OK);
	}

}

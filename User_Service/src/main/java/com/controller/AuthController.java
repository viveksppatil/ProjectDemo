package com.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.LogRequest;
import com.DTO.RegisterRequest;
import com.Service.AuthenticationService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication APIs", description = "All Authentication APIs")
public class AuthController {

	@Autowired
	private AuthenticationService as;

	@PostMapping("/signup")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "User Registerd Successfully"),
			@ApiResponse(responseCode = "400", description = "Bad Request") })
	public ResponseEntity<?> userSignUp(@Valid @RequestBody RegisterRequest user) {

		log.debug("User Requested for registration: " + user);

		return new ResponseEntity(as.userSignUpService(user), HttpStatus.OK);
	}

	@PostMapping("/login")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "User Authenticated Successfully."),
			@ApiResponse(responseCode = "401", description = "Unauthorised, Invalid Credentials") })
	public ResponseEntity<?> userLogIn(@Valid @RequestBody LogRequest lr) {
		log.debug("authenticated({})", lr.getEmail());
		return ResponseEntity.ok(as.authenticate(lr));

	}
}

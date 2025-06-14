package com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.RegisterRequest;
import com.DTO.UserResponse;
import com.Entity.User;
import com.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@Tag(name = "User Operation APIs")
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService us;

	@Operation(summary = "Get Authenticated User", description = "Retrieves the details of the currently authenticated user.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Successfully retrieved the authenticated user's details"),
			@ApiResponse(responseCode = "401", description = "Unauthorized - The user is not authenticated") })

	@GetMapping("/me")
//	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<UserResponse> getAuthenticatedUser() {
		log.debug("getAuthenticatedUser()");
		return ResponseEntity.ok(us.getAuthenticatedUser());
	}

	@Operation(summary = "Update User", description = "Updates the user details for the specified user ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User successfully updated"),
			@ApiResponse(responseCode = "400", description = "Bad Request - Invalid input"),
			@ApiResponse(responseCode = "404", description = "User not found") })
	@PatchMapping("updateUser/{id}")
	public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody @Valid RegisterRequest registerRequest) {
		log.debug("updateUser({}, {})", id, registerRequest.getEmail());
		return ResponseEntity.ok(us.updateUserInservice(id, registerRequest));
	}

	@Operation(summary = "Get Kyc Status")
	@ApiResponses({ @ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "200", description = "User successfully updated") })

	@PostMapping("/{id}/kyc-status")
	public Mono<Boolean> getKycStatus(@PathVariable int id) {
		return Mono.just(us.getKycStatusService(id));
	}

}

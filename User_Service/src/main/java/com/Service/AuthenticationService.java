package com.Service;

import javax.validation.Valid;

import com.DTO.LogRequest;
import com.DTO.LogResponse;
import com.DTO.RegisterRequest;
import com.DTO.UserResponse;

public interface AuthenticationService {
	
	public UserResponse userSignUpService(RegisterRequest user);

	public LogResponse authenticate(@Valid LogRequest lr);

}

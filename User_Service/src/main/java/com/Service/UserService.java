package com.Service;

import javax.validation.Valid;

import com.DTO.RegisterRequest;
import com.DTO.UserResponse;
import com.Entity.User;

public interface UserService {

	public UserResponse getAuthenticatedUser();

	public UserResponse updateUserInservice(int id, @Valid RegisterRequest registerRequest);

	public boolean getKycStatusService(int id);


}

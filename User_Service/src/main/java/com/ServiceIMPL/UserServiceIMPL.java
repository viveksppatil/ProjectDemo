package com.ServiceIMPL;

import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.DTO.RegisterRequest;
import com.DTO.UserResponse;
import com.Entity.User;
import com.ExceptionHandler.UserNotFound;
import com.Service.UserService;
import com.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceIMPL implements UserService {

	@Autowired
	private UserRepo ur;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserResponse getAuthenticatedUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		System.out.println(principal);

		User currentUser = (User) principal;

		log.info("Authenticated user email: {}", currentUser.getEmail());

		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(currentUser, UserResponse.class);
	}

	@Override
	public UserResponse updateUserInservice(int id, @Valid RegisterRequest rr) {

		User user = ur.findById(id).orElseThrow(() -> new UserNotFound("User Not Exists"));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(rr, user);
		user.setPassword(passwordEncoder.encode(rr.getPassword()));

		User updatedUser = ur.save(user);

		return modelMapper.map(updatedUser, UserResponse.class);
	}

	@Override
	public boolean getKycStatusService(int id) {

		User user = ur.findById(id).orElseThrow(() -> new UserNotFound("User Not Exists"));

		return user.getKycstatus().name().equalsIgnoreCase("FULLY_VERIFIED") ? true:false;
	}

}

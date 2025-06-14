package com.ServiceIMPL;

import java.time.Instant; 
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.DTO.LogRequest;
import com.DTO.LogResponse;
import com.DTO.RegisterRequest;
import com.DTO.UserResponse;
import com.Entity.Role;
import com.Entity.User;
import com.Enum.KycStatusEnum;
import com.Enum.RolesEnum;
import com.Enum.UserStatusEnum;
import com.ExceptionHandler.RoleNotFound;
import com.ExceptionHandler.UserAlreadyExists;
import com.Service.AuthenticationService;
import com.Service.JwtService;
import com.repository.RoleRepo;
import com.repository.UserRepo;
	
@Service
public class AuthenticationServiceIMPL implements AuthenticationService {

	@Autowired
	private UserRepo ur;

	@Autowired
	private RoleRepo rr;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtService jwtService;

	@Override
	public UserResponse userSignUpService(RegisterRequest user) {

		if (ur.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExists("User Already Exist");
		}

		Role role = rr.findByName(RolesEnum.USER).orElseThrow(() -> new RoleNotFound("Not foun"));

		ModelMapper mm = new ModelMapper();

		User us = mm.map(user, User.class);
		us.setPassword(passwordEncoder.encode(user.getPassword()));
		us.setKycstatus(KycStatusEnum.PENDING);
		us.setUserstatus(UserStatusEnum.ACTIVE);
		us.setRole(role);
		User us1 = ur.save(us);

		UserResponse ur = mm.map(us1, UserResponse.class);

		return ur;
	}

	@Override
	public LogResponse authenticate(@Valid LogRequest lr) {

		authManager.authenticate(new UsernamePasswordAuthenticationToken(lr.getEmail(), lr.getPassword()));

		User user = ur.findByEmail(lr.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User Not Found."));

		Instant date = Instant.now();

		User updatedUser = ur.save(user);

		String token = jwtService.generateToken(updatedUser);

		return LogResponse.builder().userId(updatedUser.getId()).token(token)
				.expiresAt(date.plusMillis(jwtService.getExpirationTime()).toString()).build();
	}
}

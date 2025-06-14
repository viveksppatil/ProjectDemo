package com.Security;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomeUserDetails extends UserDetails {

	String getEmail();
}

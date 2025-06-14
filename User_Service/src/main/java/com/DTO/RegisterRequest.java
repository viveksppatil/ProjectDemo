package com.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
@Validated
public class RegisterRequest {

	@JsonProperty(value = "uname")
	@NotBlank(message = "Username is Required")
	private String username;

	@JsonProperty(value = "uemail")
	@NotBlank(message = "Email is Required")
	@Email(message = "Invalid Email")
	private String email;

	@JsonProperty(value = "ufname")
	@NotBlank(message = "Firstname is Required")
	private String firstName;

	@JsonProperty(value = "umname")
	@NotBlank(message = "Middlename is Required")
	private String middleName;

	@JsonProperty(value = "ulname")
	@NotBlank(message = "Lastname is Required")
	private String lastName;

	@JsonProperty(value = "umobileno")
	@NotBlank(message = "Mobile is Required")
	private String mobileNumber;

	@JsonProperty(value = "upassword")
	@NotBlank(message = "Password is Required")
//	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*?&])[A-Za-z\\\\d@$!%*?&]{8,}$", message = "Password must be at least 8 character, include uppercase, lowercase letters, digit and special character.")
	private String password;

}




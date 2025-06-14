package com.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogRequest {
	
	@JsonProperty(value = "uemail")
	@NotBlank(message = "Email is Required")
	@Email(message = "Invalid Email")
	@Schema(example = "example@gmail.com")
	private String email;
	
	@JsonProperty(value = "upassword")
	@NotBlank(message = "Password is Required")
	private String password;
}

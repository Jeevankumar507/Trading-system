package com.cognizant.trader.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TraderDto {

	private Long id;

	private Long tradingCompanyId;

	@NotBlank(message = "First NAme is required")
	private String firstName;

	@NotBlank(message = "Last name is Required")
	private String lastName;

	@Email(message = "Email Validation")
	private String email;

	@Pattern(regexp = "^(\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid Phone Number")
	private String phoneNumber;

	private String createdAt;
}

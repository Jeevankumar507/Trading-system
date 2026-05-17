package com.cognizant.tradingCompany.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TradingCompanyRequestDto {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 150, message = "Name must be between 3 and 150 characters")
    private String name;

    @NotBlank(message = "License number is required")
    @Size(max = 100, message = "License number must not exceed 100 characters")
    private String licenseNumber;

    @NotBlank(message = "Contact email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Contact email must not exceed 100 characters")
    private String contactEmail;

    @NotBlank(message = "Address is required")
    @Size(max = 250, message = "Address must not exceed 250 characters")
    private String address;

    @NotNull(message = "Created date is required")
    @PastOrPresent(message = "Created date cannot be in the future")
    private LocalDateTime createdAt;
}

/*
@Data
public class TradingCompanyRequestDto {

	@NotBlank(message = "Name Cannot be blank")
	@Size(min = 3,max = 150,message = "Name must be between 3 and 150 Characters")
	private String name;

	@NotBlank(message = "License Number is required")
	private String licenseNumber;

	@Email(message = "Invalid Email formate")
	private String contactEmail;

	@NotBlank(message = "Address is Required")
	private String address;
}

package com.cognizant.tradingCompany.dto;
*/

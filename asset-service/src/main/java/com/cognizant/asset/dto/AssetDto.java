package com.cognizant.asset.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetDto {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 150, message = "Name must be at most 150 characters")
    private String name;

    @NotBlank(message = "Symbol is mandatory")
    @Size(max = 20, message = "Symbol must be at most 20 characters")
    private String symbol;

    @NotBlank(message = "Type is mandatory")
    @Size(max = 50, message = "Type must be at most 50 characters")
    private String type;

    @NotNull(message = "Current price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal currentPrice;

    @NotNull(message = "Listed date is mandatory")
    @PastOrPresent(message = "Listed date cannot be in the future")
    private LocalDate listedSince;
}

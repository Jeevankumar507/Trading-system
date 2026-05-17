package com.cognizant.asset.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
	
	private String traceId;
	private int status;
	private String error;
	private String message;
	private String path;
	@Builder.Default
	private long timestamp = Instant.now().toEpochMilli();

}

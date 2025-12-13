package com.cloudeagle.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiExecutionResult {

    private boolean success;
    private int statusCode;
    private String responseBody;
    private String errorMessage;
}

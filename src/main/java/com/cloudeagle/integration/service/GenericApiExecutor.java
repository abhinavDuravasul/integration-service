package com.cloudeagle.integration.service;

import com.cloudeagle.integration.dto.ApiExecutionResult;
import com.cloudeagle.integration.entity.ExternalApiConfig;

public interface GenericApiExecutor {
    ApiExecutionResult execute(ExternalApiConfig config);
}

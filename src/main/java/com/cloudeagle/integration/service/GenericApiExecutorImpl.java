package com.cloudeagle.integration.service;

import com.cloudeagle.integration.dto.ApiExecutionResult;
import com.cloudeagle.integration.entity.ExternalApiConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@Service
public class GenericApiExecutorImpl implements GenericApiExecutor{


    private final WebClient webClient;

    public GenericApiExecutorImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public ApiExecutionResult execute(ExternalApiConfig config) {

        try {
            String response = webClient
                    .method(HttpMethod.valueOf(config.getHttpMethod()))
                    .uri(config.getUrl())
                    .header(
                            config.getAuthHeader(),
                            "Bearer " + config.getAuthToken()
                    )
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return new ApiExecutionResult(true, 200, response, null);

        } catch (WebClientResponseException ex) {

            log.warn("API call failed: status={}, body={}",
                    ex.getStatusCode(), ex.getResponseBodyAsString());

            return new ApiExecutionResult(
                    false,
                    ex.getStatusCode().value(),
                    null,
                    ex.getResponseBodyAsString()
            );

        } catch (Exception ex) {

            log.error("Unexpected error while calling API", ex);

            return new ApiExecutionResult(
                    false,
                    500,
                    null,
                    ex.getMessage()
            );
        }
    }


}

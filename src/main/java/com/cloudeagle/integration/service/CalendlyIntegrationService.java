package com.cloudeagle.integration.service;

import com.cloudeagle.integration.dto.ApiExecutionResult;
import com.cloudeagle.integration.entity.ExternalApiConfig;
import com.cloudeagle.integration.entity.TempUser;
import com.cloudeagle.integration.repository.ExternalApiConfigRepository;
import com.cloudeagle.integration.repository.TempUserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;


@Service
public class CalendlyIntegrationService {

    private final ExternalApiConfigRepository apiRepo;
    private final GenericApiExecutor executor;
    private final TempUserRepository userRepo;
    private final ObjectMapper mapper = new ObjectMapper();

    public CalendlyIntegrationService(ExternalApiConfigRepository apiRepo, GenericApiExecutor executor, TempUserRepository userRepo) {
        this.apiRepo = apiRepo;
        this.executor = executor;
        this.userRepo = userRepo;
    }

    public void fetchUsers() throws Exception {

        ExternalApiConfig config = apiRepo
                .findByAppNameAndOperation("CALENDLY", "FETCH_USERS")
                .orElseThrow(() ->
                        new IllegalStateException("No API config for CALENDLY / FETCH_USERS"));

        ApiExecutionResult result = executor.execute(config);


        if (!result.isSuccess()) {
            // log and exit
            System.out.println("Calendly API call failed with status: "
                    + result.getStatusCode());
            return;
        }

        //  Extract JSON string
        String responseBody = result.getResponseBody();

        //  Parse JSON
        JsonNode root = mapper.readTree(responseBody);
        JsonNode users = root.get("collection");

        if (users == null || !users.isArray()) {
            return;
        }

        for (JsonNode u : users) {
            TempUser user = new TempUser();
            user.setExternalId(u.get("uri").asText());
            user.setName(u.get("name").asText());
            user.setEmail(u.has("email") ? u.get("email").asText() : null);
            user.setSourceApp("CALENDLY");

            userRepo.save(user);
        }
    }

}

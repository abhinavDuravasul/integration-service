package com.cloudeagle.integration.controller;

import com.cloudeagle.integration.service.CalendlyIntegrationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegrationController {
    private final CalendlyIntegrationService service;

    public IntegrationController(CalendlyIntegrationService service) {
        this.service = service;
    }

    @GetMapping("/integrations/calendly/users")
    public String fetchCalendlyUsers() throws Exception {
        service.fetchUsers();
        return "Calendly users fetched";
    }

}


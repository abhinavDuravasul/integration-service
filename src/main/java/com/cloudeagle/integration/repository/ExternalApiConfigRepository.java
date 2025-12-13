package com.cloudeagle.integration.repository;

import com.cloudeagle.integration.entity.ExternalApiConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExternalApiConfigRepository extends JpaRepository<ExternalApiConfig, Long> {


    Optional<ExternalApiConfig>  findByAppNameAndOperation (String appName, String operation);
}

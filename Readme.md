# _**Backend Integration Service**_

### **_Overview:_**

This project demonstrates a configurable backend service built with Spring Boot to integrate with third-party SaaS applications.
Instead of hardcoding API details, integrations are driven through database configuration, allowing new systems to be added with minimal code changes.

Calendly is implemented as a sample integration to showcase the approach.

## **Approach & Design Decisions**

All external API details (URL, HTTP method, auth headers, tokens, etc.) are stored in a database table.

A single generic API executor is responsible for making outbound API calls, irrespective of the source system.

Individual integration services (e.g. Calendly) focus only on:

Fetching the required configuration

Handling response parsing

Persisting normalized user data

## **Technology Stack:**

Java 21
Spring Boot 3.2.x
Spring Data JPA
Spring WebClient
H2 
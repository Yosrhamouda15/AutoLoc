package tn.esprit.autoloc.autolocapi.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "AutoLoc API",
        version = "1.0",
        description = "API de gestion de location de véhicules"))
public class OpenApiConfig {
}
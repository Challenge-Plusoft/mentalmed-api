package br.com.fiap.mentalmed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class DocumentationConfig {

   @Bean
   public OpenAPI customOpenAPI(){
      return new OpenAPI()
               .info(new Info()
                  .title("Mental MED API")
                  .version("V1")
                  .description("API para criação de conversa")
                  .contact(new Contact().name("Vinícius Yoda").email("vyoda4604@gmail.com"))   
               )
               .components(new Components()
                        .addSecuritySchemes("bearer-key",
                              new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                              .bearerFormat("JWT")));
   }
   
}

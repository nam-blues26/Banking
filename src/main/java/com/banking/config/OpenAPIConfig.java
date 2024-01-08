package com.banking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
// lấy đường dẫn trong application.properties : http://localhost:8000
    @Value("${open.api.dev-url}")
    private String devUrl;

    // Bean cấu hình swagger
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Info info = new Info()
                .title("Tutorial Management API")
                .version("1.0");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}

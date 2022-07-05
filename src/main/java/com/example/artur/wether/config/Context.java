package com.example.artur.wether.config;

import io.minio.MinioClient;
import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Context {
    @Autowired
    private CamelContext camelContext;

    @Bean
    public MinioClient createClient() {
        MinioClient.Builder builder = new MinioClient.Builder();
        builder.endpoint("localhost", 9000, false);
        builder.credentials("Gc61dCFQQvK0Gjd7", "3euaPV85oqNtGryIrZlCC9WCAzRyH0qn");
        MinioClient minioClient = builder.build();
        camelContext.getRegistry().bind("minioClient", minioClient);
        return minioClient;

    }
}

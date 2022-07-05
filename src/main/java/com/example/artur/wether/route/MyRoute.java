package com.example.artur.wether.route;

import com.example.artur.wether.dto.WetherData;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.minio.MinioConstants;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class MyRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().host("api.openweathermap.org").component("http");
        from("timer:scheduler?period=6000")
                .to("rest:get:data/2.5/weather?lat=39.099724&lon=-94.578331&dt=1643803200&appid=a9cd5fccc22ee75510073f05585209b5")
                .unmarshal(new JacksonDataFormat(WetherData.class)).marshal().json(JsonLibrary.Jackson)
                .process(exchange -> {
                    exchange.getIn().setHeader(MinioConstants.DESTINATION_BUCKET_NAME, "test");
                    exchange.getIn().setHeader(MinioConstants.OBJECT_NAME, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHH24mmss"))+".txt");
                })
                .to("minio://test?minioClient=#minioClient&accessKey=Gc61dCFQQvK0Gjd7&secretKey=3euaPV85oqNtGryIrZlCC9WCAzRyH0qn");
    }
}

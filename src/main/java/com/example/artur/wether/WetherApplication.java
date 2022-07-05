package com.example.artur.wether;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class WetherApplication {

    public static void main(String[] args) {

        Map<String,String> map = new HashMap<>();
        map.put(null,null);
        map.put(null,"1");

        ConcurrentHashMap<String,String> stringStringConcurrentHashMap = new ConcurrentHashMap<>();
        stringStringConcurrentHashMap.remove("sda");

        SpringApplication.run(WetherApplication.class, args);
    }

}

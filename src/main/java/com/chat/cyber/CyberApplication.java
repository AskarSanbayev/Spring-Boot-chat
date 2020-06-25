package com.chat.cyber;

import com.chat.cyber.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class CyberApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyberApplication.class, args);
    }

}

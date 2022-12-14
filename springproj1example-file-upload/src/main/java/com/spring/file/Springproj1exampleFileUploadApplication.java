package com.spring.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.spring.file.config.FileConfig;

@SpringBootApplication
@EnableConfigurationProperties({
    FileConfig.class})
public class Springproj1exampleFileUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springproj1exampleFileUploadApplication.class, args);
	}

}

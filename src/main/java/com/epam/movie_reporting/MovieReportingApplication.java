package com.epam.movie_reporting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaClient
@SpringBootApplication
public class MovieReportingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieReportingApplication.class, args);
    }

}

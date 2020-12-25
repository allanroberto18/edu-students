package com.alr.school.students;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableDiscoveryClient
@SpringBootApplication
public class StudentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentsApplication.class, args);
    }

}

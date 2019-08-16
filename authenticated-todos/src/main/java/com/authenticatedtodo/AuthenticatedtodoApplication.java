package com.authenticatedtodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AuthenticatedtodoApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(AuthenticatedtodoApplication.class, args);
    }
}

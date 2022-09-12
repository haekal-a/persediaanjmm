package com.tamu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//        (exclude={SecurityAutoConfiguration.class})
public class PertamuanApplication {

    public static void main(String[] args) {
        SpringApplication.run(PertamuanApplication.class, args);
    }
}

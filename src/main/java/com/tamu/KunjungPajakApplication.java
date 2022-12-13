package com.tamu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//        (exclude={SecurityAutoConfiguration.class})
public class KunjungPajakApplication {

    public static void main(String[] args) {
        SpringApplication.run(KunjungPajakApplication.class, args);
    }
}

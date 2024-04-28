package com.olamiredev.mhospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MHospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MHospitalApplication.class, args);
    }

}

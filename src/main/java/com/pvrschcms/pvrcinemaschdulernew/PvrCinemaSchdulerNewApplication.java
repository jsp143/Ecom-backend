package com.pvrschcms.pvrcinemaschdulernew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PvrCinemaSchdulerNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(PvrCinemaSchdulerNewApplication.class, args);
	}

}

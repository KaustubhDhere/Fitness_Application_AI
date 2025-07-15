package com.fitness.activityService;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActivityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityServiceApplication.class, args);
	}

//	@PostConstruct
//	public void init() {
//		System.out.println("   _____ _  _                        _             ");
//		System.out.println("  |  ___(_)| |_  ___  _ __   ___  __| | ___  _ __  ");
//		System.out.println("  | |_  | || __|/ _ \\| '__| / _ \\/ _` |/ _ \\| '_ \\ ");
//		System.out.println("  |  _| | || |_| (_) | |   |  __/ (_| | (_) | | | |");
//		System.out.println("  |_|   |_| \\__|\\___/|_|    \\___|\\__,_|\\___/|_| |_|");
//		System.out.println("          == FITNESS APPLICATION ==");
//	}
}

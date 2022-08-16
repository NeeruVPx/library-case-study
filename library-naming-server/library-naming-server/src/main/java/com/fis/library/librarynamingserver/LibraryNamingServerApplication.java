package com.fis.library.librarynamingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class LibraryNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryNamingServerApplication.class, args);
	}

}

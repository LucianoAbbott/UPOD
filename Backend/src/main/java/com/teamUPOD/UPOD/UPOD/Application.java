package com.teamUPOD.UPOD.UPOD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Home of the main method and getters for static class
 * 
 * @author luciano abbott
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	/**
	 * Main method for the backend, starts the spring application
	 * @param args
	 */
    public static void main(String[] args) {
    		SpringApplication.run(Application.class, args);
    }
}
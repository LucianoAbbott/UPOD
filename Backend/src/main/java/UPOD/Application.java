package UPOD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Home of the main method and getters for static class
 * 
 * @author luciano abbott
 */
@SpringBootApplication
public class Application {
	/**
	 * Main method for the backend, starts the spring application
	 * @param args
	 */
    public static void main(String[] args) {
    		SpringApplication.run(Application.class, args);
    }
}
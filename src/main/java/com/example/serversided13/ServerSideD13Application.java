package com.example.serversided13;

import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerSideD13Application {
	private static final Logger logger = LoggerFactory.getLogger(ServerSideD13Application.class);

	public static void main(String[] args) {
		logger.info("Workshop 13");
		SpringApplication app = new SpringApplication(ServerSideD13Application.class);
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List<String> optsVal = appArgs.getOptionValues("dataDir");

		logger.info("List taken in: " + optsVal);
		logger.info("Current working directory: " + System.getProperty("user.dir"));

		Path dirPath = null;

		if (optsVal == null || optsVal.get(0) == null) {
			System.err.println("Directory does not exist.");
			System.exit(1);

		} else {
			// if both above conditions are not met, get port number from optsVal
			dirPath = Path.of((String) optsVal.get(0));

			// dirPath = Path.of((String) optsVal.get(0));
			logger.info("Path provided: " + dirPath.toString());

			if (!dirPath.toFile().exists()) {
				logger.info("Directory doesn't exist");
				dirPath.toFile().mkdirs();
				logger.info("Directory created");
			} else {
				logger.info("Directory exists");
			}

		}

		app.run(args);
	}

}

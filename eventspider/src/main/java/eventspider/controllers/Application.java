package eventspider.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.log4j.*;

/**
 * Application startup controller
 * @author Sebastian Greenholtz
 */
@SpringBootApplication
public class  Application {

    private static final Logger log = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        log.info("******Starting Event Spider******");
        SpringApplication.run(Application.class, args);
    }

}

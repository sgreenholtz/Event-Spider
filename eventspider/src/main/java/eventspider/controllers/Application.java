package eventspider.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Application startup controller
 * @author Sebastian Greenholtz
 */
@SpringBootApplication
public class  Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

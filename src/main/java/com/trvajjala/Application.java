package com.trvajjala;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 *
 * @author ThirupathiReddy V
 *
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    /** logger instance */
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        LOG.info("Running Spring boot application");
        SpringApplication.run(Application.class, args);
    }

}

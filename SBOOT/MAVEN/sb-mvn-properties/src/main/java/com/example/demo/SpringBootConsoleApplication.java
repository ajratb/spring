package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("project.properties")
public class SpringBootConsoleApplication
        implements CommandLineRunner {

    @Value("${some}")
    String hello;
    @Value("${java.version}")
    String version;
    @Value("${example}")
    String blabla;
    @Value("${example2}")
    String ohohoh;
    @Value("${tomcat.version}")
    String tomcat;

    private static Logger LOG = LoggerFactory
            .getLogger(SpringBootConsoleApplication.class);

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");
        LOG.info(hello);
        LOG.info(version);
        LOG.info(blabla);
        LOG.info(ohohoh);
        LOG.info(tomcat);
        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}", i, args[i]);
        }
    }
}

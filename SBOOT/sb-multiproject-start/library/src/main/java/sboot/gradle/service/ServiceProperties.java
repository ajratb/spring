package sboot.gradle.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("service")// will be used in application project
public class ServiceProperties {

    /**
     * A message for the service
     */
    private String message;

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}

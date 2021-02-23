package sboot.actuator;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
//import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author ayrat
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().requestMatchers(EndpointRequest
                .to(HealthEndpoint.class))
                .permitAll()
//                .requestMatchers(EndpointRequest
//                .to(InfoEndpoint.class))
//                .permitAll()
                .requestMatchers(EndpointRequest.toAnyEndpoint())
                .hasRole("ACTUATOR").and().httpBasic();
        //get credentials from console: user, ec0ff171-fcaf-4ad4-be31-bc6a8d336c72
    }

}

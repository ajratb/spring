package ru.wts.security_examples;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfigExamples {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    //    @Bean
//    public UserDetailsService userDetailsService() {
//        // @formatter:off
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("restapi.test02@SPM")
//                .password("test@1234")
//                .roles("USER")
//                .build();
//        // @formatter:on
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    //    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails user = User.withUsername("restapi.test02@SPM")
//                .password("test@1234") .authorities("read")
//                .build();
//                userDetailsManager.createUser(user);
//                return userDetailsManager;
//    }
}

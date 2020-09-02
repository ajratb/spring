package ru.wts;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@PropertySource("application-oauth2.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//            .antMatchers("/oauth_login", "/loginFailure", "/")
//            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .oauth2Login()
//            .loginPage("/oauth_login")
//            .authorizationEndpoint()
//            .baseUri("/oauth2/authorize-client")
//            .authorizationRequestRepository(authorizationRequestRepository())
//            .and()
//            .tokenEndpoint()
//            .accessTokenResponseClient(accessTokenResponseClient())
//            .and()
//            .defaultSuccessUrl("/loginSuccess")
//            .failureUrl("/loginFailure")
                ;
    }

//    @Bean
//    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
//        return new HttpSessionOAuth2AuthorizationRequestRepository();
//    }
//
//    @Bean
//    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
//        DefaultAuthorizationCodeTokenResponseClient accessTokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
//        return accessTokenResponseClient;
//    }
//
//    
//    // additional configuration for non-Spring Boot projects
//    private static List<String> clients = Arrays.asList("google", "facebook");
//
//    //@Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        List<ClientRegistration> registrations = clients.stream()
//            .map(c -> getRegistration(c))
//            .filter(registration -> registration != null)
//            .collect(Collectors.toList());
//
//        return new InMemoryClientRegistrationRepository(registrations);
//    }
//
//    private static String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";
//
//    @Autowired
//    private Environment env;
//
//    private ClientRegistration getRegistration(String client) {
//        String clientId = env.getProperty(CLIENT_PROPERTY_KEY + client + ".client-id");
//
//        if (clientId == null) {
//            return null;
//        }
//
//        String clientSecret = env.getProperty(CLIENT_PROPERTY_KEY + client + ".client-secret");
//        if (client.equals("google")) {
//            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .build();
//        }
//        if (client.equals("facebook")) {
//            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .build();
//        }
//        return null;
//    }

}

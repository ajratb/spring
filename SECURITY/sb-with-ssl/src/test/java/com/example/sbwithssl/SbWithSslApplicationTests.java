package com.example.sbwithssl;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbWithSslApplicationTests {

    private static final String WELCOME_URL = "https://localhost:8443/welcome";

    @Value("${trust.store}")
    private Resource trustStore;

    @Value("${trust.store.password}")
    private String trustStorePassword;

    @Test
    public void whenGETanHTTPSResource_thenCorrectResponse() throws Exception {
        ResponseEntity<String> response = restTemplate().getForEntity(WELCOME_URL, String.class, Collections.emptyMap());

        assertEquals("ssl/welcome", response.getBody());
//        assertEquals("<h1>Welcome to Secured Site</h1>", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    RestTemplate restTemplate() throws Exception {

        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

//        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
//                .loadTrustMaterial(null, acceptingTrustStrategy)
//                .build();
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray(), acceptingTrustStrategy)
                .build();
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
        HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(socketFactory)
                .build();
        HttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .evictExpiredConnections()
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(factory);
    }

    @Test
    void contextLoads() {
    }

}

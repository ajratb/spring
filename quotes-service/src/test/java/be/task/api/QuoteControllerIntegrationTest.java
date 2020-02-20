package be.task.api;

import static org.assertj.core.api.Assertions.*;

import java.net.URL;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuoteControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private URL baseURL;// error page is expected
    private URL elvlURL;
    private URL qURL;
    private URL quotesURL;
    private URL isinElvlsURL;

    @Autowired
    private TestRestTemplate apiTemplate;

    @BeforeEach
    public void setUp() throws Exception {

        String base = "http://localhost:" + port;

        this.baseURL = new URL(base);

        this.elvlURL = new URL(base + "/elvl/test12312355");

        this.quotesURL = new URL(base + "/quotes");

        this.isinElvlsURL = new URL(base + "/elvls");
    }

    @Test
    public void getElvl() throws Exception {

        ResponseEntity<String> response = apiTemplate.getForEntity(elvlURL.toString(),
                String.class);

        assertThat(response.getBody()).isEqualTo("2442347.4565");
    }

    @Test
    void getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result
                = apiTemplate.exchange(quotesURL.toString(), HttpMethod.GET, entity, String.class);

        result.getBody();
        result.getHeaders().size();
        result.getStatusCode();
        result.getStatusCodeValue();

        System.out.println("|||||||" + result + "|||||||");
        System.out.println("|||||||BODY:|" + result.getBody() + "|||||||");
        System.out.println("|||||||HEADERS SIZE:|" + result.getHeaders().size() + "|||||||");
        System.out.println("|||||||STATUS CODE:|" + result.getStatusCode() + "|||||||");
        System.out.println("|||||||STATUS CODE VALUE:|" + result.getStatusCodeValue() + "|||||||");
    }

    @Test
    void getElvls() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result
                = apiTemplate.exchange(isinElvlsURL.toString(), HttpMethod.GET, entity, String.class);

        result.getBody();
        result.getHeaders().size();
        result.getStatusCode();
        result.getStatusCodeValue();

        System.out.println("|||||||" + result + "|||||||");
        System.out.println("|||||||BODY:|" + result.getBody() + "|||||||");
        System.out.println("|||||||HEADERS SIZE:|" + result.getHeaders().size() + "|||||||");
        System.out.println("|||||||STATUS CODE:|" + result.getStatusCode() + "|||||||");
        System.out.println("|||||||STATUS CODE VALUE:|" + result.getStatusCodeValue() + "|||||||");
    }
}

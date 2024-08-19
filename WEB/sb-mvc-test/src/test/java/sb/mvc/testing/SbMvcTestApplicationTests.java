package sb.mvc.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sb.mvc.testing.gs.HomeController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SbMvcTestApplicationTests {

    @Autowired
    private HomeController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}

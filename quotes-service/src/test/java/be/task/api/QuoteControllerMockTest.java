package be.task.api;

import be.task.dao.QuoteDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class QuoteControllerMockTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    QuoteDao dao;

    @Test
    public void testGetAll() throws Exception {
        this.mockMvc.perform(get("/quotes")).andDo(print()).andExpect(status().isOk());
    }
}

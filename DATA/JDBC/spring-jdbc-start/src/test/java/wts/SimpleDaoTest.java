package wts;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringJdbcConfig.class)
public class SimpleDaoTest {

    @Autowired
    SimpleDao dao;

    @Test
    public void createSimplePerson() {

        int count = dao.getRowsCount();
        System.out.println("***** count is: " + count);
        assertThat(count).isEqualTo(4);
    }
}

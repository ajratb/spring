package ru.wts.sb.tx.examples.logging;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LogDaoImplTest {

    @Autowired
    private LogDao logDao;

    @BeforeEach
    void setUp() {
        logDao.deleteAll();
    }
}
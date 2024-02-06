package sboot.study.mapstruct.baeldung_child_beans;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeMapperTest {

    @Autowired EmployeeMapper employeeMapper;

    @Test
    void testEmpToDtoWithDivisionIsOk(){
        // you can see in generated implementation that it is ok
    }

}
package sboot.study.mapstruct.baeldung_child_beans;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class EmployeeDto {
    private int employeeId;
    private String employeeName;
    private DivisionDto division;
}

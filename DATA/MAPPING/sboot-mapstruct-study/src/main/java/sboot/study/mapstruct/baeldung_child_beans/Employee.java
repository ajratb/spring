package sboot.study.mapstruct.baeldung_child_beans;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Employee {
    private int id;
    private String name;
    private Division division;
}

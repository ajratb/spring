package sboot.jpa.links.study.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Employee {

    @Id
    @GeneratedValue
    long id;

    String name;

    public Employee(String name) {
        this.name = name;
    }
}

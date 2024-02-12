package sboot.jpa.links.study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    @Setter
    @ManyToOne
    Employee boss;

    @JoinColumn(name = "parent_dep")
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    Department parentDepartment;

    public Department(String name) {
        this.name = name;
    }
}

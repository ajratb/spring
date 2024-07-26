package sb.jdbc.audited.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    Integer version;

    String name;

    public MyUser() {
    }

    public MyUser(String name) {
        this.name = name;
    }
}

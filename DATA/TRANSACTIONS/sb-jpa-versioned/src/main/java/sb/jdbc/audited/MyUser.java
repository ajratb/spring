package sb.jdbc.audited;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Version
    Integer ver;

    String name;

    public MyUser() {
    }

    public MyUser(String name) {
        this.name = name;
    }
}

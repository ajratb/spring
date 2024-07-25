package sb.jdbc.audited;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

@SuppressWarnings({"SqlResolve", "SqlNoDataSourceInspection"})
@Setter
@Getter
@Entity
@SQLDelete(sql = "UPDATE my_user SET deleted = true WHERE id=?")
@SQLRestriction("deleted=false")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

//    @Version
//    Integer ver;

    String name;

    boolean deleted = Boolean.FALSE;

    public MyUser() {
    }

    public MyUser(String name) {
        this.name = name;
    }
}

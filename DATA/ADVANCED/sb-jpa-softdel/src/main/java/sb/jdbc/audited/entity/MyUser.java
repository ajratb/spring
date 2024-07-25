package sb.jdbc.audited.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
//import org.hibernate.annotations.SQLRestriction;

@SuppressWarnings({"SqlResolve", "SqlNoDataSourceInspection"})
@Setter
@Getter
@Entity
@SQLDelete(sql = "UPDATE my_user SET deleted = true WHERE id=?")
//@SQLRestriction("deleted=false")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    Integer version;

    String name;

    boolean deleted = Boolean.FALSE;

    public MyUser() {
    }

    public MyUser(String name) {
        this.name = name;
    }
}

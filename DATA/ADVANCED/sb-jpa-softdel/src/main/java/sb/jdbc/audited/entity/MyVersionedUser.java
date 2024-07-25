package sb.jdbc.audited.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

@SuppressWarnings({"SqlResolve", "SqlNoDataSourceInspection"})
@Setter
@Getter
@Entity
@SQLDelete(sql = "UPDATE my_versioned_user SET deleted = true, version = version + 1 WHERE id=? and version=?")
//@SQLRestriction("deleted=false")
public class MyVersionedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Version
    Integer version;

    String name;

    boolean deleted = Boolean.FALSE;

    public MyVersionedUser() {
    }

    public MyVersionedUser(String name) {
        this.name = name;
    }
}

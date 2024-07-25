package sb.jdbc.audited;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

@SuppressWarnings({"SqlResolve", "SqlNoDataSourceInspection"})
@Setter
@Getter
@Entity
@SQLDelete(sql = "UPDATE my_user SET deleted = true WHERE id=? and version=?")
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

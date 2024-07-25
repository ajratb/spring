package sb.jdbc.audited.entity;

import jakarta.persistence.Entity;
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
public class MyUser extends BaseEntity {

    Integer version;

    String name;

    public MyUser() {
    }

    public MyUser(String name) {
        this.name = name;
    }
}

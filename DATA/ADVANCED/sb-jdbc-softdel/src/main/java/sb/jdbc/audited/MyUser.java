package sb.jdbc.audited;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

@Setter
@Getter
public class MyUser {

    @Id
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

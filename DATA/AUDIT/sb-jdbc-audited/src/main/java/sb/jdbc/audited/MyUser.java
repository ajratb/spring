package sb.jdbc.audited;


import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
public class User {

    @Id
    Long id;

    String name;
}

package sb.data.multipledatasources.postgres;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

@Entity(name = "genre")
//@Table("public.genre")
@Getter
@Setter
public class Genre {
//    @jakarta.persistence.Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @jakarta.persistence.Column(name = "id", nullable = false)
//    private Long genreId;

    //    @Id
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Column(name ="genre_id")
    private Long genreId;
    @jakarta.persistence.Column(name ="genre_name")
    private String genreName;
}
package sb.jpa.criteria;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
@Entity
public class Book extends BaseEntity {

    @NotBlank
    @Size(min = 0, max = 20)
    private String title;

    @NotBlank
    @Size(min = 0, max = 30)
    private String author;

    private UUID uuid = UUID.fromString("e8ae610f-b887-4faa-950e-28d05090f8cb");
}


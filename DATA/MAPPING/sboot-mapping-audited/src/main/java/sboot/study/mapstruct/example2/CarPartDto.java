package sboot.study.mapstruct.example2;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class CarPartDto {

    private Long id;

    private String typeCode;

    private OffsetDateTime releaseDate;

    private Long supplier;

}

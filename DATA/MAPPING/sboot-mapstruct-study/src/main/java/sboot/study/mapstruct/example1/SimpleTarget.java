package sboot.study.mapstruct.example1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SimpleTarget {
    String name; String description; String targetValue; String stringFromLongValue;
}

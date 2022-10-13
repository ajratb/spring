package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/*
 * https://www.baeldung.com/jackson-serialize-enums
 */
@Getter @ToString
@RequiredArgsConstructor
public class Person {

    final String firstName;
    final String lastName;
    final Gender gender;
    final int age;

    public enum Gender {
        @JsonProperty("Male") MALE,
        @JsonProperty("Female") FEMALE
//        @JsonValue
//        private final String value;
    }
}

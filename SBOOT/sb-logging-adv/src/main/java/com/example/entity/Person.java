package com.example.entity;

import lombok.Getter;

import java.util.UUID;

public class Person {

    @Getter
    UUID id;

    public Person() {
        id = UUID.randomUUID();
    }
}

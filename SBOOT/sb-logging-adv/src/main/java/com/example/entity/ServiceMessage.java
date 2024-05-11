package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class ServiceMessage {

    UUID id;
    UUID bodyId;
    String bodyClass;
    String bodyJson;
}

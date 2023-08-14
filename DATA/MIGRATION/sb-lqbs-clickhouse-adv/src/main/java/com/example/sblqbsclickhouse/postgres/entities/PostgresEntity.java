package com.example.sblqbsclickhouse.postgres.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("public.genre")
@Getter @Setter
public class PostgresEntity {
    @Id
    @Column("genre_id")
    private Long genreId;
    @Column("genre_name")
    private String genreName;
}
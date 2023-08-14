package com.example.sblqbsclickhouse.clickhouse.db.entities.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("simple")
@Getter @Setter
public class ClickHouseEntity {
    @Id
    @Column("WatchID")
    private Long watchId;
    @Column("Title")
    private String title;
}
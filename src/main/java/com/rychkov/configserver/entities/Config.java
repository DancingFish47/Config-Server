package com.rychkov.configserver.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "configs")
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "version")
    private Integer version;
}

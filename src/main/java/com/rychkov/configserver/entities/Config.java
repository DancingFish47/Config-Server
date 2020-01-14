package com.rychkov.configserver.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
@Entity
@Document(collection = "configs")
public class Config {
    @Id
    private String id;

    private String name;

    private String version;
}

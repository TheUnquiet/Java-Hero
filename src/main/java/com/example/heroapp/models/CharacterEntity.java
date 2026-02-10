package com.example.heroapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "characters")
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    public void setName(String n) {
        name = n;
    }

    public void setType(String t) {
        type = t;
    }

    public String getId() {
        return id.toString();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}

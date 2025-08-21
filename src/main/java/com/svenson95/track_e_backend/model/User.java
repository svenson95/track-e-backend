package com.svenson95.track_e_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String name;
    private String email;
    private Number weight;

    public User() {}

    public User(String name, String email, Number weight) {
        this.name = name;
        this.email = email;
        this.weight = weight;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Number getWeight() { return weight; }
    public void setWeight(Number weight) { this.weight = weight; }
}

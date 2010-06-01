package net.dromard.demenagement.shared.model;

import java.io.Serializable;

public class Destination implements Model, Serializable {

    /** The serialVersionUID. */
    private static final long serialVersionUID = 5033571718638548165L;

    private String name;

    private String description;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package net.dromard.demenagement.shared.model;

import java.io.Serializable;

public class Destination implements Serializable {
    private static final long serialVersionUID = 5033571718638548165L;

    private String name;

    private String description;

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

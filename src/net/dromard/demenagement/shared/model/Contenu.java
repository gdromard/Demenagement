package net.dromard.demenagement.shared.model;

import java.io.Serializable;

public class Contenu implements Serializable {
    private static final long serialVersionUID = -4025520257839180867L;

    private String name;

    private String description;

    private int quantity = 1;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantite) {
        this.quantity = quantite;
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

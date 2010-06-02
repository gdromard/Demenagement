package net.dromard.demenagement.shared.model;

import java.io.Serializable;

public class Content implements Model, Serializable {

    /** The serialVersionUID. */
    private static final long serialVersionUID = -4025520257839180867L;

    private int id;

    private int cartonId;

    private String name;

    private String description;

    private int quantity = 1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCartonId(int cartonId) {
        this.cartonId = cartonId;
    }

    public int getCartonId() {
        return cartonId;
    }

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

package net.dromard.demenagement.shared.model;

import java.io.Serializable;

public class Carton implements Model, Serializable {

    private static final long serialVersionUID = -4984816144263505451L;

    private int number;

    private int primaryDestination;

    private int secondaryDestination;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrimaryDestination() {
        return primaryDestination;
    }

    public void setPrimaryDestination(int destination) {
        this.primaryDestination = destination;
    }

    public int getSecondaryDestination() {
        return secondaryDestination;
    }

    public void setSecondaryDestination(int destination) {
        this.secondaryDestination = destination;
    }
}

package net.dromard.demenagement.shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carton implements Model, Serializable {

    private static final long serialVersionUID = -4984816144263505451L;

    private int numero;

    private int primaryDestination;

    private int secondaryDestination;

    private List<Contenu> contenus;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Contenu> getContenus() {
        return contenus;
    }

    public void setContenus(List<Contenu> contenu) {
        this.contenus = contenu;
    }

    public void add(Contenu contenu) {
        if (this.contenus == null) {
            this.contenus = new ArrayList<Contenu>();
        }
        this.contenus.add(contenu);
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

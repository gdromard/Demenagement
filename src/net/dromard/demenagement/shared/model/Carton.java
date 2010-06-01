package net.dromard.demenagement.shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carton implements Model, Serializable {

    /** The serialVersionUID. */
    private static final long serialVersionUID = -3863747761025844663L;

    private int numero;

    private List<Destination> destinations;

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

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void add(Destination destination) {
        if (this.destinations == null) {
            this.destinations = new ArrayList<Destination>();
        }
        this.destinations.add(destination);
    }

    public void setDestination(List<Destination> destinations) {
        this.destinations = destinations;
    }
}

package net.dromard.demenagement.shared.model;

import java.io.Serializable;
import java.util.Date;

public class Demenagement implements Model, Serializable {

    /** The serialVersionUID. */
    private static final long serialVersionUID = 6134250505265477170L;

    private Date date;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

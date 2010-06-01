package net.dromard.demenagement.shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Demenagement implements Serializable {
    private static final long serialVersionUID = 6134250505265477170L;

    private Date date;

    private List<Carton> cartons;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Carton> getCartons() {
        return cartons;
    }

    public void setCartons(List<Carton> cartons) {
        this.cartons = cartons;
    }

    public void add(Carton carton) {
        if (this.cartons == null) {
            this.cartons = new ArrayList<Carton>();
        }
        this.cartons.add(carton);
    }
}

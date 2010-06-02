package net.dromard.demenagement.server.repository;

import java.io.File;

import net.dromard.demenagement.shared.model.Carton;

public class CartonTable extends AbstractTable<Carton> {

    @Override
    protected String getComment() {
        return "Carton Table";
    }

    @Override
    protected String[] getHeader() {
        return new String[] { "ID", "PRIMARY_DESTINATION", "SECONDARY_DESTINATION" };
    }

    @Override
    protected String[] toArray(Carton model) {
        if (model != null) {
            return new String[] { "" + model.getId(), model.getPrimaryDestination() + "", model.getSecondaryDestination() + "" };
        }
        return null;
    }

    @Override
    protected Carton toModel(String[] values) {
        Carton carton = new Carton();
        if (values.length >= 3) {
            carton.setId(Integer.parseInt(values[0]));
            carton.setPrimaryDestination(Integer.parseInt(values[1]));
            carton.setSecondaryDestination(Integer.parseInt(values[2]));
            return carton;
        }
        return null;
    }

    @Override
    protected File getFile() {
        return new File("CartonTable.csv");
    }
}

package net.dromard.demenagement.server.repository;

import java.io.File;
import java.util.Date;

import net.dromard.demenagement.shared.model.Demenagement;

public class DemenagementTable extends AbstractTable<Demenagement> {

    @Override
    protected String getComment() {
        return "Destination Table";
    }

    @Override
    protected String[] getHeader() {
        return new String[] { "ID", "DATE" };
    }

    @Override
    protected String[] toArray(Demenagement model) {
        if (model != null && model.getDate() != null) {
            return new String[] { "" + model.getId(), model.getDate().getTime() + "" };
        }
        return null;
    }

    @Override
    protected Demenagement toModel(String[] values) {
        Demenagement model = new Demenagement();
        if (values.length >= 2) {
            model.setId(Integer.parseInt(values[0]));
            model.setDate(new Date(Long.parseLong(values[1])));
            return model;
        }
        return null;
    }

    @Override
    protected File getFile() {
        return new File("DemenagementTable.csv");
    }
}

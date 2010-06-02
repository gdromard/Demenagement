package net.dromard.demenagement.server.repository;

import java.io.File;

import net.dromard.demenagement.shared.model.Destination;

public class DestinationTable extends AbstractTable<Destination> {

    @Override
    protected String getComment() {
        return "Destination Table";
    }

    @Override
    protected String[] getHeader() {
        return new String[] { "ID", "NAME", "DESCRIPTION" };
    }

    @Override
    protected String[] toArray(Destination model) {
        if (model != null) {
            return new String[] { "" + model.getId(), model.getName(), model.getDescription() };
        }
        return null;
    }

    @Override
    protected Destination toModel(String[] values) {
        Destination destination = new Destination();
        if (values.length >= 3) {
            destination.setId(Integer.parseInt(values[0]));
            destination.setName(values[1]);
            destination.setDescription(values[2]);
            return destination;
        }
        return null;
    }

    @Override
    protected File getFile() {
        return new File("DestinationTable.csv");
    }
}

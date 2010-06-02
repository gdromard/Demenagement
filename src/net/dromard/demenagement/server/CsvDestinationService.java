package net.dromard.demenagement.server;

import java.io.IOException;
import java.util.List;

import net.dromard.demenagement.shared.model.Destination;

public class CsvDestinationService extends CsvAbstractService<Destination> {
    private static final long serialVersionUID = 2297142752491559333L;

    @Override
    protected List<Destination> retrieve() throws IOException {
        return new CsvRepository().getDestinations();
    }

    @Override
    protected void save(List<Destination> list) throws IOException {
        new CsvRepository().saveDestinations(models);
    }

    @Override
    protected boolean synchronize(Destination master, Destination slave) {
        slave.setName(master.getName());
        slave.setDescription(master.getDescription());
        return true;
    }
}

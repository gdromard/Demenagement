package net.dromard.demenagement.server;

import java.io.IOException;
import java.util.List;

import net.dromard.demenagement.shared.model.Demenagement;
import net.dromard.demenagement.shared.services.DemenagementService;

public class CsvDemenagementService extends CsvAbstractService<Demenagement> implements DemenagementService {
    private static final long serialVersionUID = 7692491178153080764L;

    @Override
    protected List<Demenagement> retrieve() throws IOException {
        return new CsvRepository().getDemenagements();
    }

    @Override
    protected void save(List<Demenagement> list) throws IOException {
        new CsvRepository().saveDemenagements(models);
    }

    @Override
    protected boolean synchronize(Demenagement master, Demenagement slave) {
        slave.setDate(master.getDate());
        return true;
    }
}

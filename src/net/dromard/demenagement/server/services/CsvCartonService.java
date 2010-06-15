package net.dromard.demenagement.server.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.dromard.demenagement.server.repository.CsvRepository;
import net.dromard.demenagement.shared.model.Carton;
import net.dromard.demenagement.shared.model.Destination;
import net.dromard.demenagement.shared.services.CartonService;

public class CsvCartonService extends CsvAbstractService<Carton> implements CartonService {
    private static final long serialVersionUID = 3094308138196214403L;

    @Override
    protected List<Carton> retrieve() throws IOException {
        return new CsvRepository().getCartons();
    }

    @Override
    protected void save(List<Carton> list) throws IOException {
        new CsvRepository().saveCartons(models);
    }

    @Override
    protected boolean synchronize(Carton master, Carton slave) {
        slave.setNumero(master.getNumero());
        slave.setPrimaryDestination(master.getPrimaryDestination());
        slave.setSecondaryDestination(master.getSecondaryDestination());
        return true;
    }

    public List<Carton> getFilteredListBy(Destination destination) {
        List<Carton> entireList = getList();
        List<Carton> filtered = new ArrayList<Carton>();
        for (Carton model : entireList) {
            if (destination.getId() == model.getPrimaryDestination() || destination.getId() == model.getSecondaryDestination()) {
                filtered.add(model);
            }
        }
        return filtered;
    }
}

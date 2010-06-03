package net.dromard.demenagement.server.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.dromard.demenagement.server.repository.CsvRepository;
import net.dromard.demenagement.shared.model.Carton;
import net.dromard.demenagement.shared.model.Content;

public class CsvContentService extends CsvAbstractService<Content> {
    private static final long serialVersionUID = -2248100904241032476L;

    @Override
    protected List<Content> retrieve() throws IOException {
        return new CsvRepository().getContents();
    }

    @Override
    protected void save(List<Content> list) throws IOException {
        new CsvRepository().saveContents(list);
    }

    @Override
    protected boolean synchronize(Content master, Content slave) {
        slave.setName(master.getName());
        slave.setDescription(master.getDescription());
        slave.setQuantity(master.getQuantity());
        slave.setCartonId(master.getCartonId());
        return true;
    }

    public List<Content> getFilteredListBy(Carton carton) {
        List<Content> entireList = getList();
        List<Content> filtered = new ArrayList<Content>();
        for (Content model : entireList) {
            if (carton.getId() == model.getCartonId()) {
                filtered.add(model);
            }
        }
        return filtered;
    }
}

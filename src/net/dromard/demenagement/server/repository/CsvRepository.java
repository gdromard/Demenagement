package net.dromard.demenagement.server.repository;

import java.io.IOException;
import java.util.List;

import net.dromard.demenagement.shared.model.Carton;
import net.dromard.demenagement.shared.model.Content;
import net.dromard.demenagement.shared.model.Demenagement;
import net.dromard.demenagement.shared.model.Destination;

public class CsvRepository {
    private final Table<Demenagement> demenagementTable = new DemenagementTable();

    private final Table<Destination> destinationTable = new DestinationTable();

    private final Table<Carton> cartonTable = new CartonTable();

    private final Table<Content> contentTable = new ContentTable();

    public void saveDemenagements(List<Demenagement> demenagements) throws IOException {
        demenagementTable.save(demenagements);
    }

    public void saveDestinations(List<Destination> destinations) throws IOException {
        destinationTable.save(destinations);
    }

    public void saveCartons(List<Carton> cartons) throws IOException {
        cartonTable.save(cartons);
    }

    public void saveContents(List<Content> content) throws IOException {
        contentTable.save(content);
    }

    public List<Demenagement> getDemenagements() throws IOException {
        return demenagementTable.get();
    }

    public List<Destination> getDestinations() throws IOException {
        return destinationTable.get();
    }

    public List<Carton> getCartons() throws IOException {
        return cartonTable.get();
    }

    public List<Content> getContents() throws IOException {
        return contentTable.get();
    }
}

package net.dromard.demenagement.server;

import net.dromard.demenagement.server.repository.CartonTable;
import net.dromard.demenagement.server.repository.DemenagementTable;
import net.dromard.demenagement.server.repository.DestinationTable;
import net.dromard.demenagement.server.repository.Table;
import net.dromard.demenagement.shared.model.Carton;
import net.dromard.demenagement.shared.model.Demenagement;
import net.dromard.demenagement.shared.model.Destination;

public class CSVRepository {
    private final Table<Demenagement> demenagementTable = new DemenagementTable();

    private final Table<Destination> destinationTable = new DestinationTable();

    private final Table<Carton> cartonTable = new CartonTable();

    public void save() {

    }
}

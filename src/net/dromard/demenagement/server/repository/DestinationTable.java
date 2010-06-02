package net.dromard.demenagement.server.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.dromard.demenagement.shared.model.Destination;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class DestinationTable implements Table<Destination> {
    private static File destinationTableFile = new File("DestinationTable.csv");

    private static int nextId;

    public void save(List<Destination> catons) throws IOException {
        if (catons.size() > 0) {
            System.out.println("[DEBUG] Saving demenagement list into " + destinationTableFile.getAbsolutePath());
            if (!destinationTableFile.exists()) {
                destinationTableFile.createNewFile();
            }
            CsvWriter writer = new CsvWriter(destinationTableFile.getAbsolutePath());
            writer.setEscapeMode(CsvWriter.ESCAPE_MODE_BACKSLASH);
            writer.setDelimiter('|');
            writer.writeComment("Destination Table");
            writer.writeComment("Fields: ID|PRIMARY_DESTINATION|SECONDARY_DESTINATION");
            for (Destination destination : catons) {
                writer.writeRecord(new String[] { "" + destination.getId(), destination.getName(), destination.getDescription() });
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<Destination> get() throws IOException {
        if (!destinationTableFile.exists()) {
            System.out.println("[DEBUG] Creating empty demenagement list");
            return new ArrayList<Destination>();
        }
        nextId = -1;
        CsvReader reader = new CsvReader(destinationTableFile.getAbsolutePath());
        reader.setEscapeMode(CsvWriter.ESCAPE_MODE_BACKSLASH);
        reader.setDelimiter('|');
        List<Destination> destinations = new ArrayList();
        try {
            while (reader.readRecord()) {
                String[] values = reader.getValues();
                Destination destination = new Destination();

                if (values.length >= 2) {
                    destination.setId(Integer.parseInt(values[0]));
                    destination.setName(values[1]);
                    destination.setDescription(values[2]);
                    nextId = Math.max(destination.getId(), nextId);
                }
                destinations.add(destination);
            }
        } finally {
            reader.close();
        }
        ++nextId;
        return destinations;
    }
}

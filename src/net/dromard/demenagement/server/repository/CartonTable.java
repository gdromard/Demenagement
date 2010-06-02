package net.dromard.demenagement.server.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.dromard.demenagement.shared.model.Carton;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CartonTable implements Table<Carton> {
    private static File cartonTableFile = new File("CartonTable.csv");

    private static int nextId;

    public void save(List<Carton> catons) throws IOException {
        if (catons.size() > 0) {
            System.out.println("[DEBUG] Saving demenagement list into " + cartonTableFile.getAbsolutePath());
            if (!cartonTableFile.exists()) {
                cartonTableFile.createNewFile();
            }
            CsvWriter writer = new CsvWriter(cartonTableFile.getAbsolutePath());
            writer.setEscapeMode(CsvWriter.ESCAPE_MODE_BACKSLASH);
            writer.setDelimiter('|');
            writer.writeComment("Carton Table");
            writer.writeComment("Fields: ID|PRIMARY_DESTINATION|SECONDARY_DESTINATION");
            for (Carton carton : catons) {
                writer.writeRecord(new String[] { "" + carton.getId(), carton.getPrimaryDestination() + "", carton.getSecondaryDestination() + "" });
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<Carton> get() throws IOException {
        if (!cartonTableFile.exists()) {
            System.out.println("[DEBUG] Creating empty demenagement list");
            return new ArrayList<Carton>();
        }
        nextId = -1;
        CsvReader reader = new CsvReader(cartonTableFile.getAbsolutePath());
        reader.setEscapeMode(CsvWriter.ESCAPE_MODE_BACKSLASH);
        reader.setDelimiter('|');
        List<Carton> cartons = new ArrayList();
        try {
            while (reader.readRecord()) {
                String[] values = reader.getValues();
                Carton carton = new Carton();

                if (values.length >= 2) {
                    carton.setId(Integer.parseInt(values[0]));
                    carton.setPrimaryDestination(Integer.parseInt(values[1]));
                    carton.setSecondaryDestination(Integer.parseInt(values[2]));
                    nextId = Math.max(carton.getId(), nextId);
                }
                cartons.add(carton);
            }
        } finally {
            reader.close();
        }
        ++nextId;
        return cartons;
    }
}

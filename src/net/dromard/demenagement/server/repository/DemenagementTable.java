package net.dromard.demenagement.server.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.dromard.demenagement.shared.model.Demenagement;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class DemenagementTable implements Table<Demenagement> {
    private static File demenagementTableFile = new File("DemenagementTable.csv");

    private static int nextId;

    public void save(List<Demenagement> demenagements) throws IOException {
        if (demenagements.size() > 0) {
            System.out.println("[DEBUG] Saving demenagement list into " + demenagementTableFile.getAbsolutePath());
            if (!demenagementTableFile.exists()) {
                demenagementTableFile.createNewFile();
            }
            CsvWriter writer = new CsvWriter(demenagementTableFile.getAbsolutePath());
            writer.setEscapeMode(CsvWriter.ESCAPE_MODE_BACKSLASH);
            writer.setDelimiter('|');
            writer.writeComment("Demenagement Table");
            writer.writeComment("Fields: ID|DATE");
            for (Demenagement demenagement : demenagements) {
                if (demenagement.getDate() != null) {
                    writer.writeRecord(new String[] { "" + demenagement.getId(), demenagement.getDate().getTime() + "" });
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<Demenagement> get() throws IOException {
        if (!demenagementTableFile.exists()) {
            System.out.println("[DEBUG] Creating empty demenagement list");
            return new ArrayList<Demenagement>();
        }
        nextId = -1;
        CsvReader reader = new CsvReader(demenagementTableFile.getAbsolutePath());
        reader.setEscapeMode(CsvWriter.ESCAPE_MODE_BACKSLASH);
        reader.setDelimiter('|');
        List<Demenagement> demenagements = new ArrayList();
        try {
            while (reader.readRecord()) {
                String[] values = reader.getValues();
                Demenagement demenagement = new Demenagement();

                if (values.length >= 2) {
                    demenagement.setId(Integer.parseInt(values[0]));
                    demenagement.setDate(new Date(Long.parseLong(values[1])));
                    nextId = Math.max(demenagement.getId(), nextId);
                }
                demenagements.add(demenagement);
            }
        } finally {
            reader.close();
        }
        ++nextId;
        return demenagements;
    }
}

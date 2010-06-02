package net.dromard.demenagement.server.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.dromard.demenagement.shared.model.Model;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public abstract class AbstractTable<M extends Model> implements Table<M> {

    public final void save(List<M> models) throws IOException {
        if (models != null && models.size() > 0) {
            System.out.println("[DEBUG] Saving demenagement list into " + getFile().getAbsolutePath());
            if (!getFile().exists()) {
                getFile().createNewFile();
            }
            CsvWriter writer = new CsvWriter(getFile().getAbsolutePath());
            writer.setEscapeMode(CsvWriter.ESCAPE_MODE_BACKSLASH);
            writer.setDelimiter('|');
            writer.setComment('#');
            writer.writeComment(getComment());
            writer.writeRecord(getHeader());
            try {
                for (M model : models) {
                    String[] raw = toArray(model);
                    if (raw != null) {
                        writer.writeRecord(raw);
                    }
                }
            } finally {
                writer.close();
            }
        }
    }

    public final List<M> get() throws IOException {
        if (!getFile().exists()) {
            System.out.println("[DEBUG] Creating empty demenagement list");
            return new ArrayList<M>();
        }
        CsvReader reader = new CsvReader(getFile().getAbsolutePath());
        reader.setEscapeMode(CsvWriter.ESCAPE_MODE_BACKSLASH);
        reader.setDelimiter('|');
        List<M> models = new ArrayList<M>();
        try {
            reader.readRecord(); // Read Comment
            reader.readRecord(); // Read Header
            reader.setHeaders(reader.getValues());
            while (reader.readRecord()) {
                String[] values = reader.getValues();
                if (values.length >= getHeader().length) {
                    M model = toModel(values);
                    models.add(model);
                }
            }
        } finally {
            reader.close();
        }
        return models;
    }

    protected abstract File getFile();

    protected abstract M toModel(String[] values);

    protected abstract String[] toArray(M model);

    protected abstract String[] getHeader();

    protected abstract String getComment();
}

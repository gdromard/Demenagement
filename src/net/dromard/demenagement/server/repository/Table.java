package net.dromard.demenagement.server.repository;

import java.io.IOException;
import java.util.List;

import net.dromard.demenagement.shared.model.Model;

public interface Table<T extends Model> {
    void save(List<T> models) throws IOException;

    List<T> get() throws IOException;
}

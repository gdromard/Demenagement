package net.dromard.demenagement.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.dromard.demenagement.shared.model.Demenagement;
import net.dromard.demenagement.shared.services.DemenagementService;

public class DemenagementRepository implements DemenagementService {
    List<Demenagement> demenagements;

    File repository = new File("demenagements.ser");

    @Override
    public boolean addDemenagement(Demenagement demenenagement) throws IllegalArgumentException {
        try {
            if (get().add(demenenagement)) {
                save();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeDemenagement(Demenagement demenenagement) throws IllegalArgumentException {
        try {
            if (get().remove(demenenagement)) {
                save();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Demenagement> getDemenagements() {
        try {
            return get();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void save() throws IOException {
        save(repository, demenagements);
    }

    private List<Demenagement> get() throws IOException, ClassNotFoundException {
        if (demenagements == null) {
            demenagements = get(repository);
        }
        return demenagements;
    }

    private static void save(File repository, List<Demenagement> demenagements) throws IOException {
        if (demenagements.size() > 0) {
            System.out.println("[DEBUG] Saving demenagement list into " + repository.getAbsolutePath());
            if (!repository.exists()) {
                repository.createNewFile();
            }
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(repository));
            try {
                output.writeObject(demenagements);
            } finally {
                output.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Demenagement> get(File repository) throws IOException, ClassNotFoundException {
        if (!repository.exists()) {
            System.out.println("[DEBUG] Creating empty demenagement list");
            return new ArrayList<Demenagement>();
        }
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(repository));
        try {
            return (List<Demenagement>) input.readObject();
        } finally {
            input.close();
        }
    }
}

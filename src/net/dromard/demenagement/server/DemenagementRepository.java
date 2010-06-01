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
import net.dromard.demenagement.shared.model.Model;
import net.dromard.demenagement.shared.services.DemenagementService;

public class DemenagementRepository implements DemenagementService {
    private List<Demenagement> demenagements;

    private int nextId;

    File repository = new File("demenagements.ser");

    @Override
    public boolean addDemenagement(Demenagement demenenagement) throws IllegalArgumentException {
        try {
            if (get().add(demenenagement) && alreadyExist(demenenagement)) {
                demenenagement.setId(nextId);
                ++nextId;
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
    public boolean saveDemenagement(Demenagement demenenagement) throws IllegalArgumentException {
        try {
            Demenagement toSave = getDemenagement(demenenagement.getId());
            if (toSave != null) {
                toSave.setDate(demenenagement.getDate());
                toSave.setCartons(demenenagement.getCartons());
                save();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeDemenagement(Demenagement demenenagement) throws IllegalArgumentException {
        try {
            Object toDelete = getDemenagement(demenenagement.getId());
            if (get().remove(toDelete)) {
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

    private boolean alreadyExist(Demenagement newObject) {
        for (Model model : demenagements) {
            if (newObject.getId() == model.getId()) {
                return true;
            }
        }
        return false;
    }

    private Demenagement getDemenagement(long id) {
        for (Demenagement demenagement : demenagements) {
            if (id == demenagement.getId()) {
                System.out.println("[DEBUG] Object found for id: " + id);
                return demenagement;
            }
        }
        System.out.println("[WARNING] No object found for id: " + id);
        return null;
    }

    private void save() throws IOException {
        save(repository);
    }

    private List<Demenagement> get() throws IOException, ClassNotFoundException {
        if (demenagements == null) {
            demenagements = get(repository);
        }
        return demenagements;
    }

    /*
    private void print() {
        for (Demenagement demenagement : demenagements) {
            System.out.println(demenagement.getId() + " - " + demenagement.getDate());
        }
    }
    */

    private void save(File repository) throws IOException {
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
    private List<Demenagement> get(File repository) throws IOException, ClassNotFoundException {
        if (!repository.exists()) {
            System.out.println("[DEBUG] Creating empty demenagement list");
            return new ArrayList<Demenagement>();
        }
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(repository));
        try {
            demenagements = (List<Demenagement>) input.readObject();
            System.out.println("[DEBUG] Loading data from file " + repository.getAbsolutePath());

            nextId = -1;
            for (Demenagement demenagement : demenagements) {
                nextId = Math.max(demenagement.getId(), nextId);
            }
            ++nextId;

            return demenagements;
        } finally {
            input.close();
        }
    }
}

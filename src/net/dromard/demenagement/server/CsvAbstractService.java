package net.dromard.demenagement.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.dromard.demenagement.shared.model.Model;
import net.dromard.demenagement.shared.services.AbstractService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public abstract class CsvAbstractService<M extends Model> extends RemoteServiceServlet implements AbstractService<M> {
    private static final long serialVersionUID = 597653176099018308L;

    protected List<M> models = null;

    private int nextId;

    @Override
    public boolean add(M model) throws IllegalArgumentException {
        try {
            if (getList().add(model) && alreadyExist(model)) {
                model.setId(nextId);
                ++nextId;
                save(models);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean save(M model) throws IllegalArgumentException {
        try {
            M toSave = get(model.getId());
            if (toSave != null) {
                if (synchronize(model, toSave)) {
                    save(models);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remove(M model) throws IllegalArgumentException {
        try {
            Object toDelete = get(model.getId());
            if (getList().remove(toDelete)) {
                save(models);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<M> getList() {
        try {
            System.out.println("[DEBUG] models: getList()");
            if (models == null) {
                models = retrieve();
                nextId = 0;
                for (Model model : models) {
                    nextId = Math.max(nextId, model.getId());
                }
                ++nextId;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (models == null) {
            models = new ArrayList<M>();
        }
        return models;
    }

    private boolean alreadyExist(M newModel) {
        if (models != null) {
            for (Model model : models) {
                if (newModel.getId() == model.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    private M get(long id) {
        if (models != null) {
            for (M model : models) {
                if (id == model.getId()) {
                    System.out.println("[DEBUG] Object found for id: " + id);
                    return model;
                }
            }
        }
        System.out.println("[WARNING] No object found for id: " + id);
        return null;
    }

    private void print() {
        for (Model model : models) {
            System.out.println(model.getId() + " - ");
        }
    }

    protected abstract boolean synchronize(M master, M slave);

    protected abstract void save(List<M> list) throws IOException;

    protected abstract List<M> retrieve() throws IOException;
}

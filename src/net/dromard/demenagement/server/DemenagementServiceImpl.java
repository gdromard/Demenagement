package net.dromard.demenagement.server;

import java.util.List;

import net.dromard.demenagement.shared.model.Demenagement;
import net.dromard.demenagement.shared.services.DemenagementService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The client side stub for the RPC service.
 */
@SuppressWarnings("serial")
public class DemenagementServiceImpl extends RemoteServiceServlet implements DemenagementService {
    private static DemenagementRepository repo = new DemenagementRepository();

    public List<Demenagement> getDemenagements() throws IllegalArgumentException {
        return repo.getDemenagements();
    }

    @Override
    public boolean addDemenagement(Demenagement demenenagement) throws IllegalArgumentException {
        return repo.addDemenagement(demenenagement);
    }

    @Override
    public boolean saveDemenagement(Demenagement demenenagement) throws IllegalArgumentException {
        return repo.saveDemenagement(demenenagement);
    }

    @Override
    public boolean removeDemenagement(Demenagement demenenagement) throws IllegalArgumentException {
        return repo.removeDemenagement(demenenagement);
    }
}

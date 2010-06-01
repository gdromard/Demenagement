package net.dromard.demenagement.shared.services;

import java.util.List;

import net.dromard.demenagement.shared.model.Demenagement;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("demenagement")
public interface DemenagementService extends RemoteService {
    List<Demenagement> getDemenagements() throws IllegalArgumentException;

    boolean addDemenagement(Demenagement demenenagement) throws IllegalArgumentException;

    boolean removeDemenagement(Demenagement demenenagement) throws IllegalArgumentException;
}

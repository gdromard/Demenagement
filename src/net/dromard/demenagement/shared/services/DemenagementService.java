package net.dromard.demenagement.shared.services;

import net.dromard.demenagement.shared.model.Demenagement;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("demenagement.service")
public interface DemenagementService extends AbstractService<Demenagement> {
}

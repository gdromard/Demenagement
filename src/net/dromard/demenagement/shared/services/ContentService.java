package net.dromard.demenagement.shared.services;

import net.dromard.demenagement.shared.model.Content;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("content")
public interface ContentService extends AbstractService<Content> {
}

package net.dromard.demenagement.shared.services;

import java.util.List;

import net.dromard.demenagement.shared.model.Model;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * The client side stub for the RPC service.
 */
public interface AbstractService<M extends Model> extends RemoteService {
    List<M> getList() throws IllegalArgumentException;

    boolean add(M model) throws IllegalArgumentException;

    boolean save(M model) throws IllegalArgumentException;

    boolean remove(M model) throws IllegalArgumentException;
}

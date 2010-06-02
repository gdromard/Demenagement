package net.dromard.demenagement.shared.services;

import java.util.List;

import net.dromard.demenagement.shared.model.Model;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>Service</code>.
 */
public interface AbstractServiceAsync<M extends Model> {
    void getList(AsyncCallback<List<M>> callback) throws IllegalArgumentException;

    void add(M model, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

    void remove(M model, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

    void save(M model, AsyncCallback<Boolean> asyncCallback) throws IllegalArgumentException;
}

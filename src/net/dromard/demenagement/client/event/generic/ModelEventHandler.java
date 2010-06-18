package net.dromard.demenagement.client.event.generic;

import net.dromard.demenagement.shared.model.Model;

public interface ModelEventHandler<M extends Model> extends CreateModelEventHandler<M>, DeleteModelEventHandler<M>, EditModelEventHandler<M>, SelectModelEventHandler<M>, UnselectModelEventHandler<M>, CreatedModelEventHandler<M>, UpdatedModelEventHandler<M>, DeletedModelEventHandler<M>, ListChangedEventHandler {

}

package net.dromard.demenagement.client.event.demenagement;

import net.dromard.demenagement.client.event.generic.CreateModelEventHandler;
import net.dromard.demenagement.client.event.generic.CreatedModelEventHandler;
import net.dromard.demenagement.client.event.generic.DeleteModelEventHandler;
import net.dromard.demenagement.client.event.generic.DeletedModelEventHandler;
import net.dromard.demenagement.client.event.generic.EditModelEventHandler;
import net.dromard.demenagement.client.event.generic.SelectModelEventHandler;
import net.dromard.demenagement.client.event.generic.UnselectModelEventHandler;
import net.dromard.demenagement.client.event.generic.UpdatedModelEventHandler;
import net.dromard.demenagement.shared.model.Demenagement;

public interface DemenagementEventHandler extends CreateModelEventHandler<Demenagement>, DeleteModelEventHandler<Demenagement>, EditModelEventHandler<Demenagement>, SelectModelEventHandler<Demenagement>, UnselectModelEventHandler<Demenagement>, CreatedModelEventHandler<Demenagement>, UpdatedModelEventHandler<Demenagement>, DeletedModelEventHandler<Demenagement> {

}

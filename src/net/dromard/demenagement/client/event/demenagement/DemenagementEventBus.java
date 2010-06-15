package net.dromard.demenagement.client.event.demenagement;

import net.dromard.demenagement.client.event.generic.CreateModelEvent;
import net.dromard.demenagement.client.event.generic.CreateModelEventHandler;
import net.dromard.demenagement.client.event.generic.CreatedModelEvent;
import net.dromard.demenagement.client.event.generic.CreatedModelEventHandler;
import net.dromard.demenagement.client.event.generic.DeleteModelEvent;
import net.dromard.demenagement.client.event.generic.DeleteModelEventHandler;
import net.dromard.demenagement.client.event.generic.DeletedModelEvent;
import net.dromard.demenagement.client.event.generic.DeletedModelEventHandler;
import net.dromard.demenagement.client.event.generic.EditModelEvent;
import net.dromard.demenagement.client.event.generic.EditModelEventHandler;
import net.dromard.demenagement.client.event.generic.SelectModelEvent;
import net.dromard.demenagement.client.event.generic.SelectModelEventHandler;
import net.dromard.demenagement.client.event.generic.UnselectModelEvent;
import net.dromard.demenagement.client.event.generic.UnselectModelEventHandler;
import net.dromard.demenagement.client.event.generic.UpdatedModelEvent;
import net.dromard.demenagement.client.event.generic.UpdatedModelEventHandler;
import net.dromard.demenagement.client.event.template.TemplateEventBus;
import net.dromard.demenagement.client.event.template.TemplateEventHandler;
import net.dromard.demenagement.shared.model.Demenagement;
import net.dromard.mvp.client.ChildEventBus;
import net.dromard.mvp.client.DefaultRunAsyncCallBack;

import com.google.gwt.event.shared.GwtEvent.Type;

public class DemenagementEventBus extends ChildEventBus<DemenagementEventHandler, TemplateEventHandler, TemplateEventBus> implements DemenagementEventHandler {
    public static Type<CreateModelEventHandler<Demenagement>> CREATE_TYPE = new Type<CreateModelEventHandler<Demenagement>>();

    public static Type<DeleteModelEventHandler<Demenagement>> DELETE_TYPE = new Type<DeleteModelEventHandler<Demenagement>>();

    public static Type<EditModelEventHandler<Demenagement>> EDIT_TYPE = new Type<EditModelEventHandler<Demenagement>>();

    public static Type<SelectModelEventHandler<Demenagement>> SELECT_TYPE = new Type<SelectModelEventHandler<Demenagement>>();

    public static Type<UnselectModelEventHandler<Demenagement>> UNSELECT_TYPE = new Type<UnselectModelEventHandler<Demenagement>>();

    public static Type<CreatedModelEventHandler<Demenagement>> CREATED_TYPE = new Type<CreatedModelEventHandler<Demenagement>>();

    public static Type<UpdatedModelEventHandler<Demenagement>> UPDATED_TYPE = new Type<UpdatedModelEventHandler<Demenagement>>();

    public static Type<DeletedModelEventHandler<Demenagement>> DELETED_TYPE = new Type<DeletedModelEventHandler<Demenagement>>();

    public DemenagementEventBus(TemplateEventBus parentBus) {
        super(parentBus);
    }

    public void register(DemenagementEventHandler handler) {
        addHandler(CREATE_TYPE, handler);
        addHandler(DELETE_TYPE, handler);
        addHandler(EDIT_TYPE, handler);
        addHandler(SELECT_TYPE, handler);
        addHandler(UNSELECT_TYPE, handler);
        addHandler(CREATED_TYPE, handler);
        addHandler(UPDATED_TYPE, handler);
        addHandler(DELETED_TYPE, handler);
        addHandler(handler);
    }

    public void createDemenagement(final Demenagement model) {
        fireEvent(new CreateDemenagementEvent(model));
    }

    public void deleteDemenagement(final Demenagement model) {
        fireEvent(new DeleteDemenagementEvent(model));
    }

    public void editDemenagement(final Demenagement model) {
        fireEvent(new EditDemenagementEvent(model));
    }

    public void fireCreatedDemenagement(final Demenagement model) {
        fireEvent(new CreatedDemenagementEvent(model));
    }

    public void fireUpdatedDemenagement(final Demenagement model) {
        fireEvent(new UpdatedDemenagementEvent(model));
    }

    public void fireDeletedDemenagement(final Demenagement model) {
        fireEvent(new DeletedDemenagementEvent(model));
    }

    public void selectDemenagement(final int rowIndex, final Demenagement model) {
        fireEvent(new SelectDemenagementEvent(rowIndex, model));
    }

    public void unselectDemenagement(final int rowIndex, final Demenagement model) {
        fireEvent(new UnselectDemenagementEvent(rowIndex, model));
    }

    @Override
    public void onCreate(final Demenagement model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (DemenagementEventHandler handler : getHandlers()) {
                    handler.onCreate(model);
                }
            }
        };
    }

    @Override
    public void onDelete(final Demenagement model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (DemenagementEventHandler handler : getHandlers()) {
                    handler.onDelete(model);
                }
            }
        };
    }

    @Override
    public void onEdit(final Demenagement model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (DemenagementEventHandler handler : getHandlers()) {
                    handler.onEdit(model);
                }
            }
        };
    }

    @Override
    public void onCreated(final Demenagement model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (DemenagementEventHandler handler : getHandlers()) {
                    handler.onCreated(model);
                }
            }
        };
    }

    @Override
    public void onUpdated(final Demenagement model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (DemenagementEventHandler handler : getHandlers()) {
                    handler.onUpdated(model);
                }
            }
        };
    }

    @Override
    public void onDeleted(final Demenagement model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (DemenagementEventHandler handler : getHandlers()) {
                    handler.onDeleted(model);
                }
            }
        };
    }

    @Override
    public void onSelect(final int rowIndex, final Demenagement model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (DemenagementEventHandler handler : getHandlers()) {
                    handler.onSelect(rowIndex, model);
                }
            }
        };
    }

    @Override
    public void onUnselect(final int rowIndex, final Demenagement model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (DemenagementEventHandler handler : getHandlers()) {
                    handler.onUnselect(rowIndex, model);
                }
            }
        };
    }

    private static class CreateDemenagementEvent extends CreateModelEvent<Demenagement> {
        public CreateDemenagementEvent(Demenagement model) {
            super(model);
        }

        @Override
        public Type<CreateModelEventHandler<Demenagement>> getAssociatedType() {
            return CREATE_TYPE;
        }
    }

    private static class DeleteDemenagementEvent extends DeleteModelEvent<Demenagement> {
        public DeleteDemenagementEvent(Demenagement model) {
            super(model);
        }

        @Override
        public Type<DeleteModelEventHandler<Demenagement>> getAssociatedType() {
            return DELETE_TYPE;
        }
    }

    private static class EditDemenagementEvent extends EditModelEvent<Demenagement> {
        public EditDemenagementEvent(Demenagement model) {
            super(model);
        }

        @Override
        public Type<EditModelEventHandler<Demenagement>> getAssociatedType() {
            return EDIT_TYPE;
        }
    }

    private static class CreatedDemenagementEvent extends CreatedModelEvent<Demenagement> {
        public CreatedDemenagementEvent(Demenagement model) {
            super(model);
        }

        @Override
        public Type<CreatedModelEventHandler<Demenagement>> getAssociatedType() {
            return CREATED_TYPE;
        }
    }

    private static class UpdatedDemenagementEvent extends UpdatedModelEvent<Demenagement> {
        public UpdatedDemenagementEvent(Demenagement model) {
            super(model);
        }

        @Override
        public Type<UpdatedModelEventHandler<Demenagement>> getAssociatedType() {
            return UPDATED_TYPE;
        }
    }

    private static class DeletedDemenagementEvent extends DeletedModelEvent<Demenagement> {
        public DeletedDemenagementEvent(Demenagement model) {
            super(model);
        }

        @Override
        public Type<DeletedModelEventHandler<Demenagement>> getAssociatedType() {
            return DELETED_TYPE;
        }
    }

    private static class SelectDemenagementEvent extends SelectModelEvent<Demenagement> {
        public SelectDemenagementEvent(final int rowIndex, Demenagement model) {
            super(rowIndex, model);
        }

        @Override
        public Type<SelectModelEventHandler<Demenagement>> getAssociatedType() {
            return SELECT_TYPE;
        }
    }

    private static class UnselectDemenagementEvent extends UnselectModelEvent<Demenagement> {
        public UnselectDemenagementEvent(final int rowIndex, Demenagement model) {
            super(rowIndex, model);
        }

        @Override
        public Type<UnselectModelEventHandler<Demenagement>> getAssociatedType() {
            return UNSELECT_TYPE;
        }
    }
}

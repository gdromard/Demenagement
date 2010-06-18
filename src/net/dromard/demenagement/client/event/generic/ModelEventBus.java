package net.dromard.demenagement.client.event.generic;

import net.dromard.demenagement.client.event.template.TemplateEventBus;
import net.dromard.demenagement.client.event.template.TemplateEventHandler;
import net.dromard.demenagement.shared.model.Model;
import net.dromard.mvp.client.ChildEventBus;
import net.dromard.mvp.client.DefaultRunAsyncCallBack;

import com.google.gwt.event.shared.GwtEvent.Type;

public abstract class ModelEventBus<M extends Model> extends ChildEventBus<ModelEventHandler<M>, TemplateEventHandler, TemplateEventBus> implements ModelEventHandler<M> {
    public final Type<CreateModelEventHandler<M>> CREATE_TYPE = new Type<CreateModelEventHandler<M>>();

    public final Type<DeleteModelEventHandler<M>> DELETE_TYPE = new Type<DeleteModelEventHandler<M>>();

    public final Type<EditModelEventHandler<M>> EDIT_TYPE = new Type<EditModelEventHandler<M>>();

    public final Type<SelectModelEventHandler<M>> SELECT_TYPE = new Type<SelectModelEventHandler<M>>();

    public final Type<UnselectModelEventHandler<M>> UNSELECT_TYPE = new Type<UnselectModelEventHandler<M>>();

    public final Type<CreatedModelEventHandler<M>> CREATED_TYPE = new Type<CreatedModelEventHandler<M>>();

    public final Type<UpdatedModelEventHandler<M>> UPDATED_TYPE = new Type<UpdatedModelEventHandler<M>>();

    public final Type<DeletedModelEventHandler<M>> DELETED_TYPE = new Type<DeletedModelEventHandler<M>>();

    public final Type<ListChangedEventHandler> LIST_CHANGED_TYPE = new Type<ListChangedEventHandler>();

    public ModelEventBus(TemplateEventBus parentBus) {
        super(parentBus);
    }

    public void register(ModelEventHandler<M> handler) {
        addHandler(CREATE_TYPE, handler);
        addHandler(DELETE_TYPE, handler);
        addHandler(EDIT_TYPE, handler);
        addHandler(SELECT_TYPE, handler);
        addHandler(UNSELECT_TYPE, handler);
        addHandler(CREATED_TYPE, handler);
        addHandler(UPDATED_TYPE, handler);
        addHandler(DELETED_TYPE, handler);
        addHandler(LIST_CHANGED_TYPE, handler);
    }

    public void register(CreateModelEventHandler<M> handler) {
        addHandler(CREATE_TYPE, handler);
    }

    public void register(DeleteModelEventHandler<M> handler) {
        addHandler(DELETE_TYPE, handler);
    }

    public void register(EditModelEventHandler<M> handler) {
        addHandler(EDIT_TYPE, handler);
    }

    public void register(SelectModelEventHandler<M> handler) {
        addHandler(SELECT_TYPE, handler);
    }

    public void register(UnselectModelEventHandler<M> handler) {
        addHandler(UNSELECT_TYPE, handler);

    }

    public void register(CreatedModelEventHandler<M> handler) {
        addHandler(CREATED_TYPE, handler);
    }

    public void register(UpdatedModelEventHandler<M> handler) {
        addHandler(UPDATED_TYPE, handler);
    }

    public void register(DeletedModelEventHandler<M> handler) {
        addHandler(DELETED_TYPE, handler);
    }

    public void register(ListChangedEventHandler handler) {
        addHandler(LIST_CHANGED_TYPE, handler);
    }

    public void create(final M model) {
        fireEvent(new CreateEvent(model));
    }

    public void delete(final M model) {
        fireEvent(new DeleteEvent(model));
    }

    public void edit(final M model) {
        fireEvent(new EditEvent(model));
    }

    public void fireCreated(final M model) {
        fireEvent(new CreatedEvent(model));
    }

    public void fireUpdated(final M model) {
        fireEvent(new UpdatedEvent(model));
    }

    public void fireDeleted(final M model) {
        fireEvent(new DeletedEvent(model));
    }

    public void fireListChanged(final Class<? extends Model> clazz) {
        fireEvent(new LocalListChangedEvent(clazz));
    }

    public void fireSelect(final int rowIndex, final M model) {
        fireEvent(new SelectEvent(rowIndex, model));
    }

    public void fireUnselect(final int rowIndex, final M model) {
        fireEvent(new UnselectEvent(rowIndex, model));
    }

    @Override
    public void onCreate(final M model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (CreateModelEventHandler<M> handler : getHandlers(CREATE_TYPE)) {
                    handler.onCreate(model);
                }
            }
        };
    }

    @Override
    public void onDelete(final M model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (DeleteModelEventHandler<M> handler : getHandlers(DELETE_TYPE)) {
                    handler.onDelete(model);
                }
            }
        };
    }

    @Override
    public void onEdit(final M model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (EditModelEventHandler<M> handler : getHandlers(EDIT_TYPE)) {
                    handler.onEdit(model);
                }
            }
        };
    }

    @Override
    public void onCreated(final M model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (CreatedModelEventHandler<M> handler : getHandlers(CREATED_TYPE)) {
                    handler.onCreated(model);
                }
            }
        };
    }

    @Override
    public void onUpdated(final M model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (UpdatedModelEventHandler<M> handler : getHandlers(UPDATED_TYPE)) {
                    handler.onUpdated(model);
                }
            }
        };
    }

    @Override
    public void onDeleted(final M model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (DeletedModelEventHandler<M> handler : getHandlers(DELETED_TYPE)) {
                    handler.onDeleted(model);
                }
            }
        };
    }

    @Override
    public void onListChanged(final Class<? extends Model> clazz) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (ListChangedEventHandler handler : getHandlers(LIST_CHANGED_TYPE)) {
                    handler.onListChanged(clazz);
                }
            }
        };
    }

    @Override
    public void onSelect(final int rowIndex, final M model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (SelectModelEventHandler<M> handler : getHandlers(SELECT_TYPE)) {
                    handler.onSelect(rowIndex, model);
                }
            }
        };
    }

    @Override
    public void onUnselect(final int rowIndex, final M model) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (UnselectModelEventHandler<M> handler : getHandlers(UNSELECT_TYPE)) {
                    handler.onUnselect(rowIndex, model);
                }
            }
        };
    }

    private class CreateEvent extends CreateModelEvent<M> {
        public CreateEvent(M model) {
            super(model);
        }

        @Override
        public Type<CreateModelEventHandler<M>> getAssociatedType() {
            return CREATE_TYPE;
        }
    }

    private class DeleteEvent extends DeleteModelEvent<M> {
        public DeleteEvent(M model) {
            super(model);
        }

        @Override
        public Type<DeleteModelEventHandler<M>> getAssociatedType() {
            return DELETE_TYPE;
        }
    }

    private class EditEvent extends EditModelEvent<M> {
        public EditEvent(M model) {
            super(model);
        }

        @Override
        public Type<EditModelEventHandler<M>> getAssociatedType() {
            return EDIT_TYPE;
        }
    }

    private class CreatedEvent extends CreatedModelEvent<M> {
        public CreatedEvent(M model) {
            super(model);
        }

        @Override
        public Type<CreatedModelEventHandler<M>> getAssociatedType() {
            return CREATED_TYPE;
        }
    }

    private class UpdatedEvent extends UpdatedModelEvent<M> {
        public UpdatedEvent(M model) {
            super(model);
        }

        @Override
        public Type<UpdatedModelEventHandler<M>> getAssociatedType() {
            return UPDATED_TYPE;
        }
    }

    private class DeletedEvent extends DeletedModelEvent<M> {
        public DeletedEvent(M model) {
            super(model);
        }

        @Override
        public Type<DeletedModelEventHandler<M>> getAssociatedType() {
            return DELETED_TYPE;
        }
    }

    private class LocalListChangedEvent extends ListChangedEvent {
        public LocalListChangedEvent(final Class<? extends Model> clazz) {
            super(clazz);
        }

        @Override
        public Type<ListChangedEventHandler> getAssociatedType() {
            return LIST_CHANGED_TYPE;
        }
    }

    private class SelectEvent extends SelectModelEvent<M> {
        public SelectEvent(final int rowIndex, M model) {
            super(rowIndex, model);
        }

        @Override
        public Type<SelectModelEventHandler<M>> getAssociatedType() {
            return SELECT_TYPE;
        }
    }

    private class UnselectEvent extends UnselectModelEvent<M> {
        public UnselectEvent(final int rowIndex, M model) {
            super(rowIndex, model);
        }

        @Override
        public Type<UnselectModelEventHandler<M>> getAssociatedType() {
            return UNSELECT_TYPE;
        }
    }
}

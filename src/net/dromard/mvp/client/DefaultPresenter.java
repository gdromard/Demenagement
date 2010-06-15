package net.dromard.mvp.client;

public class DefaultPresenter<V extends View, PresenterEventBus extends EventBus> implements Presenter<V> {
    private V view;

    private PresenterEventBus eventBus;

    public final void setView(V view) {
        this.view = view;
    }

    public final V getView() {
        return view;
    }

    public final void setEventBus(PresenterEventBus eventBus) {
        this.eventBus = eventBus;
    }

    public final PresenterEventBus getEventBus() {
        return eventBus;
    }

    public void bind() {
    }
}

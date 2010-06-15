package net.dromard.mvp.client;


/**
 * Interface that defines a presenter.<br/>
 * <br/>
 * This interface provides getter and setter for a view and an event bus.<br/>
 * <br/>
 * It is recommended to use directly <code>BasePresenter</code>.
 * 
 * @param <V> Type of the view injected into the presenter
 */
public interface Presenter<V extends View> {

    /**
     * Gets the view associated with the presenter.
     * @return view manipulated by the presenter.
     */
    public V getView();
}

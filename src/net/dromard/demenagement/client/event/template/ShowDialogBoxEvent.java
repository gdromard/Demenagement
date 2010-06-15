package net.dromard.demenagement.client.event.template;

import net.dromard.mvp.client.AbstractEvent;

public class ShowDialogBoxEvent extends AbstractEvent<ShowDialogBoxEventHandlerIF> {
    public static Type<ShowDialogBoxEventHandlerIF> TYPE = new Type<ShowDialogBoxEventHandlerIF>();

    @Override
    public Type<ShowDialogBoxEventHandlerIF> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ShowDialogBoxEventHandlerIF handler) {
        handler.onShowDialogBox();
    }
}

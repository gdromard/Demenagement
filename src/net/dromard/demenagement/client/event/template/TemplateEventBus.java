package net.dromard.demenagement.client.event.template;

import net.dromard.mvp.client.AbstractEvent;
import net.dromard.mvp.client.DefaultRunAsyncCallBack;
import net.dromard.mvp.client.EventBus;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.Widget;

public class TemplateEventBus extends EventBus<TemplateEventHandler> implements TemplateEventHandler {
    public static Type<ChangeRightBottomWidgetEventHandler> CHANGE_RIGHT_BOTTOM_TYPE = new Type<ChangeRightBottomWidgetEventHandler>();

    public static Type<ChangeRightTopWidgetEventHandler> CHANGE_RIGHT_TOP_TYPE = new Type<ChangeRightTopWidgetEventHandler>();

    public static Type<ChangeLeftWidgetEventHandler> CHANGE_LEFT_TYPE = new Type<ChangeLeftWidgetEventHandler>();

    public static Type<ChangeFooterWidgetEventHandler> CHANGE_FOOTER_TYPE = new Type<ChangeFooterWidgetEventHandler>();

    public void register(TemplateEventHandler handler) {
        addHandler(CHANGE_RIGHT_BOTTOM_TYPE, handler);
        addHandler(CHANGE_RIGHT_TOP_TYPE, handler);
        addHandler(CHANGE_LEFT_TYPE, handler);
        addHandler(CHANGE_FOOTER_TYPE, handler);
    }

    public void changeRightBottomWidget(final Widget widget) {
        fireEvent(new ChangeRightBottomWidgetEvent(widget));
    }

    public void changeRightTopWidget(final Widget widget) {
        fireEvent(new ChangeRightTopWidgetEvent(widget));
    }

    public void changeLeftWidget(final Widget widget) {
        fireEvent(new ChangeLeftWidgetEvent(widget));
    }

    public void changeFooterWidget(Widget widget) {
        fireEvent(new ChangeFooterWidgetEvent(widget));
    }

    @Override
    public void onChangeRightBottomWidget(final Widget widget) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (ChangeRightBottomWidgetEventHandler handler : getHandlers(CHANGE_RIGHT_BOTTOM_TYPE)) {
                    handler.onChangeRightBottomWidget(widget);
                }
            }
        };
    }

    @Override
    public void onChangeRightTopWidget(final Widget widget) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (ChangeRightTopWidgetEventHandler handler : getHandlers(CHANGE_RIGHT_TOP_TYPE)) {
                    handler.onChangeRightTopWidget(widget);
                }
            }
        };
    }

    @Override
    public void onChangeLeftWidget(final Widget widget) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (ChangeLeftWidgetEventHandler handler : getHandlers(CHANGE_LEFT_TYPE)) {
                    handler.onChangeLeftWidget(widget);
                }
            }
        };
    }

    @Override
    public void onChangeFooterWidget(final Widget widget) {
        new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                for (ChangeFooterWidgetEventHandler handler : getHandlers(CHANGE_FOOTER_TYPE)) {
                    handler.onChangeFooterWidget(widget);
                }
            }
        };
    }

    private static class ChangeRightBottomWidgetEvent extends AbstractEvent<ChangeRightBottomWidgetEventHandler> {
        private final Widget widget;

        public ChangeRightBottomWidgetEvent(Widget widget) {
            this.widget = widget;
        }

        @Override
        public Type<ChangeRightBottomWidgetEventHandler> getAssociatedType() {
            return CHANGE_RIGHT_BOTTOM_TYPE;
        }

        @Override
        protected void dispatch(ChangeRightBottomWidgetEventHandler handler) {
            handler.onChangeRightBottomWidget(widget);
        }
    }

    private static class ChangeLeftWidgetEvent extends AbstractEvent<ChangeLeftWidgetEventHandler> {
        private final Widget widget;

        public ChangeLeftWidgetEvent(Widget widget) {
            this.widget = widget;
        }

        @Override
        public Type<ChangeLeftWidgetEventHandler> getAssociatedType() {
            return CHANGE_LEFT_TYPE;
        }

        @Override
        protected void dispatch(ChangeLeftWidgetEventHandler handler) {
            handler.onChangeLeftWidget(widget);
        }
    }

    private static class ChangeRightTopWidgetEvent extends AbstractEvent<ChangeRightTopWidgetEventHandler> {
        private final Widget widget;

        public ChangeRightTopWidgetEvent(Widget widget) {
            this.widget = widget;
        }

        @Override
        public Type<ChangeRightTopWidgetEventHandler> getAssociatedType() {
            return CHANGE_RIGHT_TOP_TYPE;
        }

        @Override
        protected void dispatch(ChangeRightTopWidgetEventHandler handler) {
            handler.onChangeRightTopWidget(widget);
        }
    }

    private static class ChangeFooterWidgetEvent extends AbstractEvent<ChangeFooterWidgetEventHandler> {
        private final Widget widget;

        public ChangeFooterWidgetEvent(Widget widget) {
            this.widget = widget;
        }

        @Override
        public Type<ChangeFooterWidgetEventHandler> getAssociatedType() {
            return CHANGE_FOOTER_TYPE;
        }

        @Override
        protected void dispatch(ChangeFooterWidgetEventHandler handler) {
            handler.onChangeFooterWidget(widget);
        }
    }
}

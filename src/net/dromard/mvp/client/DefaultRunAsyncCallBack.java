package net.dromard.mvp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.Window;

public abstract class DefaultRunAsyncCallBack implements RunAsyncCallback {

    @Override
    public void onFailure(Throwable reason) {
        GWT.log(reason.getMessage());
        Window.alert(reason.getMessage());
    }
}

package net.dromard.demenagement.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale(value = "fr")
public interface MyMessages extends Messages {
    String actionAdd();

    String actionCancel();

    String actionDelete();

    String actionSave();

    String actionReaload();

    String cssClassOdd();

    String errorActionFailed(String action);

    String errorServerDidNotSucceed();

    String labelCartonNumber();

    String labelCartons();

    String labelDate();

    String labelDemenagements();
}

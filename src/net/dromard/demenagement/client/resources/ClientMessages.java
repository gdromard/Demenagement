package net.dromard.demenagement.client.resources;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale(value = "fr")
public interface ClientMessages extends Messages {
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

    String actionLoad();

    String cartonLabel();

    String cartonsLabel();

    String cartonIdLabel();

    String cartonNumberLabel();

    String cartonPrimaryDestinationLabel();

    String cartonSecondaryDestinationLabel();

    String movingLabel();

    String movingsLabel();

    String movingIdLabel();

    String movingDateLabel();

    String destinationErrorNotFound();

    String questionAreYouSure();

    String buttonNo();

    String buttonYes();
}

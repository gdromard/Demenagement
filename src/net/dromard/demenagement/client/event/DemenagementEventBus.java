package net.dromard.demenagement.client.event;

import net.dromard.demenagement.client.event.generic.ModelEventBus;
import net.dromard.demenagement.client.event.template.TemplateEventBus;
import net.dromard.demenagement.shared.model.Demenagement;

public class DemenagementEventBus extends ModelEventBus<Demenagement> implements DemenagementEventHandler {

    public DemenagementEventBus(TemplateEventBus parentBus) {
        super(parentBus);
    }
}

package net.dromard.demenagement.client.event;

import net.dromard.demenagement.client.event.generic.ModelEventBus;
import net.dromard.demenagement.client.event.template.TemplateEventBus;
import net.dromard.demenagement.shared.model.Carton;

public class CartonEventBus extends ModelEventBus<Carton> implements CartonEventHandler {

    public CartonEventBus(TemplateEventBus parentBus) {
        super(parentBus);
    }
}

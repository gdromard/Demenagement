package net.dromard.widget.client;

public interface HiddenWidget extends IsWidget, HasWidget {

    /**
     * Gets this object's value.
     * @return the object's value
     */
    String getValue();

    /**
     * @param value the object's new value
     */
    void setValue(String value);
}

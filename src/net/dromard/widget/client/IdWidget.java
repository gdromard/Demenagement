package net.dromard.widget.client;

public interface IdWidget extends HasWidget {

    /**
     * Gets this object's value.
     * @return the object's value
     */
    int getId();

    /**
     * @param value the object's new value
     */
    void setId(int value);
}

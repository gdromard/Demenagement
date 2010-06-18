package net.dromard.widget.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

public interface IsWidget {

    /**
     * Gets this widget's parent panel.
     * 
     * @return the widget's parent panel
     */
    Widget getParent();

    /**
     * Removes this widget from its parent widget, if one exists.
     * 
     * <p>
     * If it has no parent, this method does nothing. If it is a "root" widget (meaning it's been added to the detach list via
     * {@link RootPanel#detachOnWindowClose(Widget)}), it will be removed from the detached immediately. This makes it possible for Composites and
     * Panels to adopt root widgets.
     * </p>
     * 
     * @throws IllegalStateException if this widget's parent does not support removal (e.g. {@link Composite})
     */
    void removeFromParent();

    /**
     * Adds a dependent style name by specifying the style name's suffix. The actual form of the style name that is added is:
     * 
     * <pre class="code">
     * getStylePrimaryName() + '-' + styleSuffix
     * </pre>
     * 
     * @param styleSuffix the suffix of the dependent style to be added.
     * @see #setStylePrimaryName(String)
     * @see #removeStyleDependentName(String)
     * @see #addStyleName(String)
     */
    void addStyleDependentName(String styleSuffix);

    /**
     * Adds a secondary or dependent style name to this object. A secondary style name is an additional style name that is, in HTML/CSS terms,
     * included as a space-separated token in the value of the CSS <code>class</code> attribute for this object's root element.
     * 
     * <p>
     * The most important use for this method is to add a special kind of secondary style name called a <i>dependent style name</i>. To add a
     * dependent style name, use {@link #addStyleDependentName(String)}, which will prefix the 'style' argument with the result of
     * {@link #getStylePrimaryName()} (followed by a '-'). For example, suppose the primary style name is <code>gwt-TextBox</code>. If the following
     * method is called as <code>obj.setReadOnly(true)</code>:
     * </p>
     * 
     * <pre class="code">
     * public void setReadOnly(boolean readOnly) {
     *     isReadOnlyMode = readOnly;
     * 
     *     // Create a dependent style name.
     *     String readOnlyStyle = &quot;readonly&quot;;
     * 
     *     if (readOnly) {
     *         addStyleDependentName(readOnlyStyle);
     *     } else {
     *         removeStyleDependentName(readOnlyStyle);
     *     }
     * }
     * </pre>
     * 
     * <p>
     * then both of the CSS style rules below will be applied:
     * </p>
     * 
     * <pre class="code">
     * 
     * // This rule is based on the primary style name and is always active.
     * .gwt-TextBox {
     *   font-size: 12pt;
     * }
     * 
     * // This rule is based on a dependent style name that is only active
     * // when the widget has called addStyleName(getStylePrimaryName() +
     * // "-readonly").
     * .gwt-TextBox-readonly {
     *   background-color: lightgrey;
     *   border: none;
     * }
     * </pre>
     * 
     * <p>
     * Dependent style names are powerful because they are automatically updated whenever the primary style name changes. Continuing with the example
     * above, if the primary style name changed due to the following call:
     * </p>
     * 
     * <pre class="code">
     * setStylePrimaryName(&quot;my-TextThingy&quot;);
     * </pre>
     * 
     * <p>
     * then the object would be re-associated with following style rules, removing those that were shown above.
     * </p>
     * 
     * <pre class="code">
     * .my-TextThingy {
     *   font-size: 20pt;
     * }
     * 
     * .my-TextThingy-readonly {
     *   background-color: red;
     *   border: 2px solid yellow;
     * }
     * </pre>
     * 
     * <p>
     * Secondary style names that are not dependent style names are not automatically updated when the primary style name changes.
     * </p>
     * 
     * @param style the secondary style name to be added
     * @see UIObject
     * @see #removeStyleName(String)
     */
    void addStyleName(String style);

    /**
     * Gets the object's absolute left position in pixels, as measured from the browser window's client area.
     * 
     * @return the object's absolute left position
     */
    int getAbsoluteLeft();

    /**
     * Gets the object's absolute top position in pixels, as measured from the browser window's client area.
     * 
     * @return the object's absolute top position
     */
    int getAbsoluteTop();

    /**
     * Gets a handle to the object's underlying DOM element.
     * 
     * This method should not be overridden. It is non-final solely to support legacy code that depends upon overriding it. If it is overridden, the
     * subclass implementation must not return a different element than was previously set using
     * {@link #setElement(com.google.gwt.user.client.Element)}.
     * 
     * @return the object's browser element
     */
    com.google.gwt.user.client.Element getElement();

    /**
     * Gets the object's offset height in pixels. This is the total height of the object, including decorations such as border, margin, and padding.
     * 
     * @return the object's offset height
     */
    int getOffsetHeight();

    /**
     * Gets the object's offset width in pixels. This is the total width of the object, including decorations such as border, margin, and padding.
     * 
     * @return the object's offset width
     */
    int getOffsetWidth();

    /**
     * Gets all of the object's style names, as a space-separated list. If you wish to retrieve only the primary style name, call
     * {@link #getStylePrimaryName()}.
     * 
     * @return the objects's space-separated style names
     * @see #getStylePrimaryName()
     */
    String getStyleName();

    /**
     * Gets the primary style name associated with the object.
     * 
     * @return the object's primary style name
     * @see #setStyleName(String)
     * @see #addStyleName(String)
     * @see #removeStyleName(String)
     */
    String getStylePrimaryName();

    /**
     * Gets the title associated with this object. The title is the 'tool-tip' displayed to users when they hover over the object.
     * 
     * @return the object's title
     */
    String getTitle();

    /**
     * Determines whether or not this object is visible.
     * 
     * @return <code>true</code> if the object is visible
     */
    boolean isVisible();

    /**
     * Removes a dependent style name by specifying the style name's suffix.
     * 
     * @param styleSuffix the suffix of the dependent style to be removed
     * @see #setStylePrimaryName(Element, String)
     * @see #addStyleDependentName(String)
     * @see #addStyleName(String)
     */
    void removeStyleDependentName(String styleSuffix);

    /**
     * Removes a style name. This method is typically used to remove secondary style names, but it can be used to remove primary stylenames as well.
     * That use is not recommended.
     * 
     * @param style the secondary style name to be removed
     * @see #addStyleName(String)
     */
    void removeStyleName(String style);

    /**
     * Sets the object's height. This height does not include decorations such as border, margin, and padding.
     * 
     * @param height the object's new height, in CSS units (e.g. "10px", "1em")
     */
    void setHeight(String height);

    /**
     * Sets the object's size. This size does not include decorations such as border, margin, and padding.
     * 
     * @param width the object's new width, in CSS units (e.g. "10px", "1em")
     * @param height the object's new height, in CSS units (e.g. "10px", "1em")
     */
    void setSize(String width, String height);

    /**
     * Clears all of the object's style names and sets it to the given style. You should normally use {@link #setStylePrimaryName(String)} unless you
     * wish to explicitly remove all existing styles.
     * 
     * @param style the new style name
     * @see #setStylePrimaryName(String)
     */
    void setStyleName(String style);

    /**
     * Sets the object's primary style name and updates all dependent style names.
     * 
     * @param style the new primary style name
     * @see #addStyleName(String)
     * @see #removeStyleName(String)
     */
    void setStylePrimaryName(String style);

    /**
     * Sets the title associated with this object. The title is the 'tool-tip' displayed to users when they hover over the object.
     * 
     * @param title the object's new title
     */
    void setTitle(String title);

    /**
     * Sets whether this object is visible.
     * 
     * @param visible <code>true</code> to show the object, <code>false</code> to hide it
     */
    void setVisible(boolean visible);

    /**
     * Sets the object's width. This width does not include decorations such as border, margin, and padding.
     * 
     * @param width the object's new width, in CSS units (e.g. "10px", "1em")
     */
    void setWidth(String width);

}

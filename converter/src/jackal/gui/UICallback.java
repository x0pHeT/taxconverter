package jackal.gui;

/**
 * User: jackal
 * Date: 26.09.13
 * Time: 14:38
 * $Rev:$
 * $Author:$
 * $Date:$
 */

/**
 * Callback class to use from {@link DBCreator} to publish data, perform UI changes etc.
 */
public interface UICallback {

    void addUserMessageFromOuterMethod(String message);

    void disableCreateButton();

    void enableCreateButton();
}

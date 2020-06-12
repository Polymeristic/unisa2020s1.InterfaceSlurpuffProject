package application;

import javafx.scene.control.Alert;

/**
 * Methods for displaying simple dialog boxes
 */
public class SimpleDialog {

    /**
     * Displays a Exception as a dialog box
     * @param e Exception information to pass through
     */
    public static void Error(Exception e) {
        New(Alert.AlertType.ERROR, "Exception Occurred", e.getMessage());
    }

    /**
     * Displays a warning message as a dialog box
     * @param title Title of the dialog box
     * @param description Description of the dialog box
     */
    public static void Error(String title, String description) { New(Alert.AlertType.ERROR, title, description ); }

    /**
     * Displays a warning message as a dialog box
     * @param title Title of the dialog box
     * @param description Description of the dialog box
     */
    public static void Warning(String title, String description) { New(Alert.AlertType.WARNING, title, description ); }

    /**
     * Displays a new dialog box and waits for it to close
     * @param type Type of dialog
     * @param title Title of the dialog box
     * @param content Text contents to show
     */
    public static void New(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);

        alert.showAndWait();
    }
}

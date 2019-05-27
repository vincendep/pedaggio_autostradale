package presentation.shared;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class Controller {
	
	protected void showAlert(String title, String content) {
		Alert alert = new Alert(AlertType.WARNING, content);
		alert.setTitle(title);
		alert.showAndWait();
	}
}

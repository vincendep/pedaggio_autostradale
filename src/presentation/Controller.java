package presentation;

import business.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public abstract class Controller {
	
	protected Main main;
	
	public void setMain(Main main) {
		this.main = main;
	}
}

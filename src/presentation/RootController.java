package presentation;

import business.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class RootController extends Controller {
	
	private Main main; 

	@FXML
	public void homeButtonHandle() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(WelcomeViewController.class.getResource("welcome-view.fxml"));
			Pane welcomeView = loader.load();
			Controller controller = loader.getController();
			controller.setMain(main);
			main.getRoot().setCenter(welcomeView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleBackButton() {
		
	}
	
	public void showView(String view, Controller controller, boolean bind) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(controller.getClass().getResource(view + ".fxml"));
			if (bind) {
				loader.setController(controller);
			} 
			Pane pane = loader.load();
			main.getRoot().setCenter(pane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import presentation.admin.highways.AdminHighwaysViewController;
import presentation.user.highways.HighwaysViewController;

public class WelcomeViewController extends Controller {
	
	@FXML
	public void userClick() {
		try {
			FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(HighwaysViewController.class.getResource("highways-view.fxml"));
    		Pane highwayView = loader.load();
    		
    		
    		Controller controller = loader.getController();
    		controller.setMain(this.main);
    		
    		main.getRoot().setCenter(highwayView);
		
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@FXML
	public void adminClick() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AdminHighwaysViewController.class.getResource("admin-highways-view.fxml"));
			Pane pane = loader.load();
			main.getRoot().setCenter(pane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

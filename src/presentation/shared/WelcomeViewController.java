package presentation.shared;

import business.Main;

import javafx.fxml.FXML;
import presentation.admin.AdminHighwaysViewController;
import presentation.user.HighwaysViewController;

public class WelcomeViewController extends Controller {
	
	@FXML
	public void userClick() {
		Main.changeScreen("highways-view", new HighwaysViewController(), false);	
	}
	
	@FXML
	public void adminClick() {
		Main.changeScreen("admin-highways-view", new AdminHighwaysViewController(), false);
	}
}

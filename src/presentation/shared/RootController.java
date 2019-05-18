package presentation.shared;

import java.util.ArrayList;
import java.util.List;

import business.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class RootController extends Controller {

	private Main main;
	private List<Screen> screens = new ArrayList<>();
	private class Screen {
		private String view;
		private Controller controller;
		private boolean bind;
		
		public Screen(String v, Controller c, boolean b) {
			view = v;
			controller = c;
			bind = b;
		}
		
		public String getView() {
			return view;
		}
		
		public Controller getController() {
			return controller;
		}
		
		public boolean getBind() {
			return bind;
		}
		
		public String toString() {
			return view;
		}
	}
	
	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	public void homeButtonHandle() {
		screens.clear();
		showView("welcome-view", new WelcomeViewController(), false);
	}
	
	@FXML
	public void handleBackButton() {
		if (screens.size() >= 2) {
			Screen previousScreen = screens.get(screens.size() - 2);
			Screen currentScreen = screens.get(screens.size() - 1);
			screens.remove(currentScreen);
			screens.remove(previousScreen);
			showView(previousScreen.getView(), previousScreen.getController(), previousScreen.getBind());
		}
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
			screens.add(new Screen(view, controller, bind));
			System.out.println(screens.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

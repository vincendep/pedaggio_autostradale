package business;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import presentation.shared.Controller;
import presentation.shared.RootController;
import presentation.shared.WelcomeViewController;

/* 
 * @author vincendep
 */
public class Main extends Application {
	
	private static RootController rootController;
	private Stage stage;
	private BorderPane root;
	
	
	public BorderPane getRoot() {
		return this.root;
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		this.stage.setTitle("Pedaggio Autostradale");
		
		initRootLayout();
		
		changeScreen("welcome-view", new WelcomeViewController(), false);
	}
	
	/*
	 * Init the root layout of the main Stage
	 */
	public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RootController.class.getResource("rootlayout.fxml"));
            root = loader.load();
            rootController = loader.getController();
            rootController.setMain(this);
            
            Scene scene = new Scene(root, 800, 500);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	/*
	 * Delegate the screen change to the root controller
	 * 
	 * @param view view to be loaded
	 * @param controller controller corresponding to the view to be located
	 * @bind tells if the controller must be attached to the view programmatically
	 */
	public static void changeScreen(String view, Controller controller, boolean bind) {
		rootController.showView(view, controller, bind);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

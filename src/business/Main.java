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
	
	public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RootController.class.getResource("rootlayout.fxml"));
            root = (BorderPane) loader.load();
            rootController = loader.getController();
            rootController.setMain(this);
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(root, 800, 500);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void changeScreen(String view, Controller controller, boolean bind) {
		rootController.showView(view, controller, bind);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

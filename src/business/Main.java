package business;

import java.io.IOException;

import business.dao.common.DaoFactory;
import business.dao.common.DaoFactory.FactoryType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import presentation.Controller;
import presentation.RootController;
import presentation.WelcomeViewController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	private Stage stage;
	private BorderPane root;
	private static RootController rootController;
	
	public BorderPane getRoot() {
		return this.root;
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		this.stage.setTitle("Pedaggio Autostradale");
		
		initRootLayout();
		
		try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(WelcomeViewController.class.getResource("welcome-view.fxml"));
    		Pane welcomeView = loader.load();
    		
    		root.setCenter(welcomeView);
    		
    		Controller welcomeViewController = loader.getController();
    		welcomeViewController.setMain(this);
    	
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
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
	
	public static void showView(String view, Controller controller, boolean bind) {
		rootController.showView(view, controller, bind);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

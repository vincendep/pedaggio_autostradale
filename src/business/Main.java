package business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import business.model.Autostrada;
import business.model.Casello;
import business.model.Normativa;
import business.model.Veicolo;
import business.model.impl.AutostradaImpl;
import business.model.impl.CaselloImpl;
import business.model.impl.Normativa2019Impl;
import business.model.impl.VeicoloImpl;
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
	
	
	public Main() {
		//dummy data A74 veicolo
		/*
		Autostrada autostrada = new AutostradaImpl();
		autostrada.setNome("A74");
		try {
			autostrada.setNormativaVigente((Normativa) Autostrada.NORMATIVA_VIGENTE.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_A, 0.04f);
		autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_B, 0.05f);
		autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_3, 0.06f);
		autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_4, 0.07f);
		autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_5, 0.08f);
		List<Casello> caselli = new ArrayList<>();
		Casello c1 = new CaselloImpl();
		c1.setAutostrada(autostrada);
		c1.setId(1);
		c1.setNome("Colledara");
		c1.setChilometro(300);
		CaselloImpl c2 = new CaselloImpl();
		c2.setAutostrada(autostrada);
		c2.setId(2);
		c2.setChilometro(350);
		c2.setNome("Val Vomano");
		caselli.add(c1);
		caselli.add(c2);
		autostrada.setCaselli(caselli);
		AUTOSTRADE.add(autostrada);
		
		Veicolo veicolo = new VeicoloImpl();
		veicolo.setId(1);
		veicolo.setModello("Panda");
		veicolo.setMarca("Fiat");
		veicolo.setNumeroAssi((byte) 2);
		veicolo.setAltezza((byte) 150);
		veicolo.setClasseAmbientale(Veicolo.ClasseAmbientale.EURO_3);
		veicolo.setTarga("XX999XX");
		VEICOLI.add(veicolo);
		*/
	}
	
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

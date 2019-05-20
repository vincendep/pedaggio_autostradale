package presentation.admin;

import business.AutostradaMgr;
import business.Main;
import business.model.Autostrada;
import business.model.impl.Normativa2019Impl;
import common.ManagerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import presentation.shared.Controller;


public class AdminHighwaysViewController extends Controller {
	
	@FXML
	private TableView<Autostrada> autostradaTable;
	@FXML
	private TableColumn<Autostrada, String> nomeColumn;
	@FXML
	private TableColumn<Autostrada, Number> tariffaClasseAColumn;
	@FXML
	private TableColumn<Autostrada, Number> tariffaClasseBColumn;
	@FXML
	private TableColumn<Autostrada, Number> tariffaClasse3Column;
	@FXML
	private TableColumn<Autostrada, Number> tariffaClasse4Column;
	@FXML
	private TableColumn<Autostrada, Number> tariffaClasse5Column;
	
	private ObservableList<Autostrada> autostrade = FXCollections.observableArrayList();
	private Autostrada autostradaSelezionata;
	
	
	public AdminHighwaysViewController() {
		try {
			autostrade.setAll(new AutostradaMgr().getAll());
		} catch (ManagerException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void initialize() {
		autostradaTable.setItems(this.autostrade);
		nomeColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		
		//TODO fix
		tariffaClasseAColumn.setCellValueFactory(cellData -> cellData.getValue().getNormativaVigente().tariffaClasseProperty(Normativa2019Impl.CLASSE_A));
		tariffaClasseBColumn.setCellValueFactory(cellData -> cellData.getValue().getNormativaVigente().tariffaClasseProperty(Normativa2019Impl.CLASSE_B));
		tariffaClasse3Column.setCellValueFactory(cellData -> cellData.getValue().getNormativaVigente().tariffaClasseProperty(Normativa2019Impl.CLASSE_3));
		tariffaClasse4Column.setCellValueFactory(cellData -> cellData.getValue().getNormativaVigente().tariffaClasseProperty(Normativa2019Impl.CLASSE_4));
		tariffaClasse5Column.setCellValueFactory(cellData -> cellData.getValue().getNormativaVigente().tariffaClasseProperty(Normativa2019Impl.CLASSE_5));

		autostradaTable.getSelectionModel().selectedItemProperty().addListener((obeservable, oldValue, newValue) -> autostradaSelezionata = newValue);
	}
	
	@FXML
	public void handleNuovo() {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(AdminNewHighwayDialogController.class.getResource("admin-highway-dialog.fxml"));
		try {
			AdminNewHighwayDialogController controller = new AdminNewHighwayDialogController();
			controller.setStage(stage);
			controller.setAutostrade(autostrade);
			loader.setController(controller);
			Pane pane = loader.load();
			stage.setScene(new Scene(pane, 700, 500));
			stage.setTitle("Crea Autostrada");
			
			stage.showAndWait();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	@FXML
	public void handleModifica() {
		if (autostradaSelezionata != null) {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AdminEditHighwayDialogController.class.getResource("admin-highway-dialog.fxml"));
			try {
				AdminEditHighwayDialogController controller = new AdminEditHighwayDialogController();
				controller.setStage(stage);
				controller.setAutostrada(autostradaSelezionata);
				loader.setController(controller);
				Pane parent = loader.load();
				stage.setScene(new Scene(parent, 700, 500));
				stage.setTitle("Modifica Autostrada");
				
				stage.showAndWait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	public void handleElimina() {
		if (autostradaSelezionata != null) {
			try {
				new AutostradaMgr().remove(autostradaSelezionata);
				autostrade.remove(autostradaSelezionata);
			} catch (ManagerException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	public void handleMostraCaselli() {
		if (autostradaSelezionata != null) {
			AdminHighwayTollboothsViewController adminHighwayTollboothsViewController = new AdminHighwayTollboothsViewController();
			adminHighwayTollboothsViewController.setAutostrada(autostradaSelezionata);
			Main.changeScreen("admin-highway-tollbooths-view", adminHighwayTollboothsViewController, true);
		}
	}
}

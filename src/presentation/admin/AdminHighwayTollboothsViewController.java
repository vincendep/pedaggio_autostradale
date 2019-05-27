package presentation.admin;

import business.manager.CaselloMgr;
import business.model.Autostrada;
import business.model.Casello;

import common.ManagerException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import presentation.shared.Controller;


public class AdminHighwayTollboothsViewController extends Controller {
	@FXML
	private Label autostradaLabel;
	@FXML
	private TableView<Casello> caselliTable;
	@FXML
	private TableColumn<Casello, String> nomeColumn;
	@FXML
	private Label identificativoLabel;
	@FXML
	private Label nomeLabel;
	@FXML
	private Label chilometroLabel;
	
	private Autostrada autostrada;
	private Casello caselloSelezionato;
	
	
	public void setAutostrada(Autostrada autostrada) {
		this.autostrada = autostrada;
	}
	
	@FXML
	public void initialize() {
		autostradaLabel.setText(autostrada.getNome());
		caselliTable.setItems(autostrada.caselliProperty());
		nomeColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		caselliTable.getSelectionModel().selectedItemProperty().addListener((obeservable, oldValue, newValue) -> {
			if (newValue != null) {
				mostraDettagliCasello(newValue);
				caselloSelezionato = newValue;
			}
		});
	}

	private void mostraDettagliCasello(Casello newValue) {
		nomeLabel.textProperty().bind(newValue.nomeProperty());
		chilometroLabel.textProperty().bind(newValue.chilometroProperty().asString());
		identificativoLabel.textProperty().bind(newValue.idProperty().asString());
	}
	
	@FXML
	public void nuovoHandle() {
		Stage stage = new Stage();
		stage.setTitle("Nuovo casello");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(AdminHighwayNewTollboothDialogController.class.getResource("admin-highway-tollbooth-dialog.fxml"));
		AdminHighwayNewTollboothDialogController controller = new AdminHighwayNewTollboothDialogController();
		controller.setAutostrada(autostrada);
		controller.setStage(stage);
		loader.setController(controller);
		try {
			Pane pane = loader.load();
			stage.setScene(new Scene(pane, 700, 500));
			stage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void modificaHandle() {
		Stage stage = new Stage();
		stage.setTitle("Modifica casello");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(AdminEditHighwayDialogController.class.getResource("admin-highway-tollbooth-dialog.fxml"));
		AdminHighwayEditTollboothDialogController controller = new AdminHighwayEditTollboothDialogController();
		controller.setCasello(caselloSelezionato);
		controller.setStage(stage);
		loader.setController(controller);
		try {
			Pane pane = loader.load();
			stage.setScene(new Scene(pane, 700, 500));
			stage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void eliminaHandle() {
		try {
			CaselloMgr.getInstance().remove(caselloSelezionato);;
			autostrada.getCaselli().remove(caselloSelezionato);
		} catch (ManagerException e) {
			e.printStackTrace();
		}
	}

}

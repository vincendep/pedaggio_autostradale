package presentation.user;

import business.AutostradaMgr;
import business.Main;
import business.model.Autostrada;
import business.model.Casello;
import common.ManagerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import presentation.shared.Controller;


public class HighwaysViewController extends Controller {
	
	@FXML
	private TableView<Autostrada> autostradaTable;
	@FXML
	private TableColumn<Autostrada, String> autostradaColumn;
	@FXML
	private TableView<Casello> caselloTable;
	@FXML
	private TableColumn<Casello, String> caselloColumn;
	@FXML
	private Label idLabel;
	@FXML
	private Label nomeLabel;
	@FXML
	private Label autostradaLabel;
	@FXML
	private Label chilometroLabel;
	
	private final ObservableList<Autostrada> autostrade = FXCollections.observableArrayList();
	private Casello caselloSelezionato;
	
	public HighwaysViewController() {
	
		try {
			autostrade.setAll(new AutostradaMgr().getAll());
		
		} catch (ManagerException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
    public void initialize() {
		autostradaTable.setItems(this.autostrade);
		autostradaTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                	caselloTable.setItems(newValue.caselliProperty());
                });
       
		caselloTable.getSelectionModel().selectedItemProperty().addListener(
        		(obeservable, oldValue, newValue) -> {
        			caselloSelezionato = newValue;
        			mostraDettagliCasello(newValue);
        		});
		
		autostradaColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        caselloColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        
	}
	
	private void mostraDettagliCasello(Casello casello) {
		if (casello != null) {
			idLabel.setText(String.valueOf(casello.getId()));
			nomeLabel.setText(casello.getNome());
			autostradaLabel.setText(casello.getAutostrada().getNome());
			chilometroLabel.setText(String.valueOf(casello.getChilometro()));
		} else {
			idLabel.setText("");
			nomeLabel.setText("");
			autostradaLabel.setText("");
			chilometroLabel.setText("");
		}
	}
	
	@FXML
	public void entra() {
		if (caselloSelezionato != null) {
			TollboothViewController controller = new TollboothViewController(caselloSelezionato);
			Main.changeScreen("tollbooth-view", controller, true);
			reset();
		}
	}
	
	private void reset() {
		caselloSelezionato = null;
	}
}

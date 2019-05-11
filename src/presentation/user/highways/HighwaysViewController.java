package presentation.user.highways;

import java.util.ArrayList;
import java.util.List;

import business.dao.common.AutostradaDao;
import business.dao.common.DaoFactory;
import business.dao.common.DaoFactory.FactoryType;
import business.model.Autostrada;
import business.model.Casello;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import presentation.Controller;
import presentation.user.tollbooth.TollboothViewController;

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
	private Label kilometroLabel;
	
	private final ObservableList<Autostrada> autostrade = FXCollections.observableArrayList();
	private Casello caselloSelezionato;
	
	public HighwaysViewController() {
		//Dummy data
		/*
		Autostrada autostrada = new Autostrada();
		autostrada.setNome("A74");
		
		List<Casello> caselli = new ArrayList<>();
		Casello c1 = new Casello();
		c1.setAutostrada(autostrada);
		c1.setId(1);
		c1.setNome("Colledara");
		c1.setKilometro(300);
		caselli.add(c1);
		
		Casello c2 = new Casello();
		c2.setAutostrada(autostrada);
		c2.setId(2);
		c2.setKilometro(350);
		c2.setNome("Val Vomano");
		caselli.add(c2);
		
		autostrada.setCaselli(caselli);
		
		this.autostrade.add(autostrada);
		*/
		
		DaoFactory factory = DaoFactory.getDaoFactory(FactoryType.MYSQL);
		AutostradaDao adao = factory.getAutostradaDao();
		autostrade.setAll(adao.getAll());
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
			kilometroLabel.setText(String.valueOf(casello.getKilometro()));
		} else {
			idLabel.setText("");
			nomeLabel.setText("");
			autostradaLabel.setText("");
			kilometroLabel.setText("");
		}
	}
	
	@FXML
	public void entra() {
		if (caselloSelezionato != null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(TollboothViewController.class.getResource("tollbooth-view.fxml"));
				TollboothViewController controller = new TollboothViewController(caselloSelezionato);
				loader.setController(controller);
				Pane tollboothView = loader.load();
				main.getRoot().setCenter(tollboothView);
				
				reset();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void reset() {
		caselloSelezionato = null;
	}
}

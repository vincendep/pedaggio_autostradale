package presentation.admin;

import java.util.List;

import business.model.Autostrada;
import business.model.Casello;
import business.model.impl.CaselloImpl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.shared.Controller;

public class AdminHighwayNewTollboothDialogController extends Controller {
	@FXML
	private TextField nomeField;
	@FXML
	private TextField chilometroField;
	@FXML
	private TextField identificativoField;
	
	private List<Casello> caselli;
	Stage stage;
	
	
	public void setCaselli(List<Casello> caselli) {
		this.caselli = caselli;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	public void handleConferma() {
		Autostrada autostrada = caselli.get(0).getAutostrada();
		Casello casello = new CaselloImpl();
		casello.setAutostrada(autostrada);
		casello.setNome(nomeField.getText());
		casello.setChilometro(Integer.parseInt(chilometroField.getText()));
		casello.setId(Integer.parseInt(identificativoField.getText()));
		
		autostrada.getCaselli().add(casello);
		stage.close();
	}
	
	@FXML
	public void handleCancella() {
		stage.close();
	}
}

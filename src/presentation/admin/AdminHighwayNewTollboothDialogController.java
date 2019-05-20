package presentation.admin;

import java.util.List;

import business.CaselloMgr;
import business.model.Autostrada;
import business.model.Casello;
import business.model.impl.CaselloImpl;
import common.ManagerException;
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
	
	private Autostrada autostrada;
	Stage stage;
	
	
	public void setAutostrada(Autostrada autostrada) {
		this.autostrada = autostrada;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	public void handleConferma() {
		try {
			Casello casello = new CaselloImpl();
			casello.setAutostrada(autostrada);
			casello.setNome(nomeField.getText());
			casello.setChilometro(Integer.parseInt(chilometroField.getText()));
			casello.setId(Integer.parseInt(identificativoField.getText()));
			
			new CaselloMgr().add(casello);
			autostrada.getCaselli().add(casello);
		
		} catch (ManagerException e) {
			e.printStackTrace();
		}
		stage.close();
	}
	
	@FXML
	public void handleCancella() {
		stage.close();
	}
}

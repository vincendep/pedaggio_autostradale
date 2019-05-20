package presentation.admin;


import business.CaselloMgr;
import business.model.Casello;
import common.ManagerException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import presentation.shared.Controller;

public class AdminHighwayEditTollboothDialogController extends Controller {
	
	@FXML
	private TextField nomeField;
	@FXML
	private TextField chilometroField;
	@FXML
	private TextField identificativoField;
	
	private Stage stage;
	private Casello casello;
	
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setCasello(Casello casello) {
		this.casello = casello;
	}
	
	@FXML
	public void initialize() {
		nomeField.setText(casello.getNome());
		chilometroField.setText(String.valueOf(casello.getChilometro()));
		identificativoField.setText(String.valueOf(casello.getId()));
	}
	
	@FXML
	public void handleConferma() {
		String nome = casello.getNome();
		try {
			casello.setNome(nomeField.getText());
			casello.setChilometro(Integer.parseInt(chilometroField.getText()));
			casello.setId(Integer.parseInt(identificativoField.getText()));
			
			new CaselloMgr().modify(nome, casello);
			
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

package presentation.admin;


import business.manager.CaselloMgr;
import business.model.Casello;
import business.model.impl.CaselloImpl;
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
		try {
			Casello c = new CaselloImpl();
			c.setNome(nomeField.getText());
			c.setChilometro(Integer.parseInt(chilometroField.getText()));
			c.setId(Integer.parseInt(identificativoField.getText()));
			
			CaselloMgr.getInstance().modify(casello.getNome(), c);
			
			casello.setId(c.getId());
			casello.setChilometro(c.getChilometro());
			casello.setNome(c.getNome());
			
		} catch (ManagerException e) {
			e.printStackTrace();
			showAlert("Impossibile modificare casello", "Il numero identificativo non è univoco");
		} catch (Exception e) {
			e.printStackTrace();
			showAlert("Impossibile modificare casello", "Identificativo e/o chilometro non validi");
		}
		stage.close();
	}
	
	@FXML
	public void handleCancella() {
		stage.close();
	}

}

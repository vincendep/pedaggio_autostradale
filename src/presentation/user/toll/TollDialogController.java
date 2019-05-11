package presentation.user.toll;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import business.model.*;

public class TollDialogController {

	@FXML
	private Label entrataLabel;
	@FXML
	private Label uscitaLabel;
	@FXML
	private Label kilometriLabel;
	@FXML
	private Label prezzoLabel;
	
	private Pedaggio pedaggio;
	
	
	public void initialize() {
		entrataLabel.setText(pedaggio.getEntrata().getNome());
		uscitaLabel.setText(pedaggio.getUscita().getNome());
		prezzoLabel.setText(String.valueOf(pedaggio.getPrezzo()));
	}
	
	public Pedaggio getPedaggio() {
		return this.pedaggio;
	}
	
	public void setPedaggio(Pedaggio pedaggio) {
		this.pedaggio = pedaggio;
	}
	
}

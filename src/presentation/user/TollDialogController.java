package presentation.user;

import business.model.Pedaggio;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TollDialogController {
	@FXML
	private Label veicoloLabel;
	@FXML
	private Label entrataLabel;
	@FXML
	private Label uscitaLabel;
	@FXML
	private Label chilometriLabel;
	@FXML
	private Label prezzoLabel;
	
	private Pedaggio pedaggio;
	private Stage stage;
	
	public void initialize() {
		veicoloLabel.setText(pedaggio.getVeicolo().getMarca() + " " + pedaggio.getVeicolo().getModello());
		entrataLabel.setText(pedaggio.getEntrata().getNome());
		uscitaLabel.setText(pedaggio.getUscita().getNome());
		chilometriLabel.setText(String.valueOf(Math.abs(pedaggio.getEntrata().getChilometro() - pedaggio.getUscita().getChilometro())));
		prezzoLabel.setText(String.valueOf(pedaggio.getPrezzo()));
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setPedaggio(Pedaggio pedaggio) {
		this.pedaggio = pedaggio;
	}
	
	@FXML
	public void paga() {
		stage.close();
	}
	
}

package presentation.user.tollbooth;

import java.io.File;

import business.model.Casello;
import business.model.Normativa2019;
import business.model.Pedaggio;
import business.model.Veicolo;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import presentation.Controller;
import presentation.user.toll.TollDialogController;

public class TollboothViewController extends Controller {
	
	@FXML
	private Label caselloLabel;
	@FXML 
	private Label autostradaLabel;
	@FXML
	private Label bigliettoLabel;
	@FXML
	private VBox dragTarget; 
	private Dragboard dragBoard;
	
	private Casello casello;
	private boolean bigliettoInserito = false;
	
	
	public TollboothViewController(Casello casello) {
		this.casello = casello;
	}
	
	public Casello getCasello() {
		return this.casello;
	}
	
	public void setCasello(Casello casello) {
		this.casello = casello;
	}
	
	@FXML
	public void initialize() {
		caselloLabel.setText(casello.getNome());
		autostradaLabel.setText(casello.getAutostrada().getNome());
		
		dragTarget.setOnDragOver(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != dragTarget
                        && event.getDragboard().getFiles().size() == 1) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });
		
		dragTarget.setOnDragDropped(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                dragBoard = event.getDragboard();
                boolean success = false;
                if (dragBoard.hasFiles()) {
                    bigliettoLabel.setText(dragBoard.getFiles().toString());
                    success = true;
                    bigliettoInserito = true;
                }
                /* let the source know whether the string was successfully 
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });
	}
	

	@FXML
	public void calcolaPedaggio() {
		// TODO
		if (bigliettoInserito) {
			//Dummy data
			Veicolo veicolo = new Veicolo();
			veicolo.setAltezza(130);
			veicolo.setNumeroAssi(2);
			veicolo.setTarga("XX666ZZ");
			Casello uscita = new Casello();
			uscita.setNome("Nowhere");
			Pedaggio pedaggio = new Pedaggio();
			pedaggio.setEntrata(casello);
			pedaggio.setUscita(uscita);
			pedaggio.setVeicolo(veicolo);
			pedaggio.setNormativa(new Normativa2019());
			pedaggio.calcolaPrezzo();
			
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(TollDialogController.class.getResource("toll-dialog.fxml"));
				TollDialogController controller = new TollDialogController();
				controller.setPedaggio(pedaggio);
				loader.setController(controller);
				Pane tollDialog = loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(tollDialog));
				reset();
				stage.showAndWait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void reset() {
		bigliettoInserito = false;
		dragBoard.clear();
		bigliettoLabel.setText("Inserisci qui il biglietto...");
	}
}

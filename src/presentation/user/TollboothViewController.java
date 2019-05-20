package presentation.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import business.Main;
import business.VeicoloMgr;
import business.model.Autostrada;
import business.model.Casello;
import business.model.Pedaggio;
import business.model.Veicolo;
import business.model.impl.CaselloImpl;
import business.model.impl.Normativa2019Impl;
import business.model.impl.PedaggioImpl;
import business.model.impl.VeicoloImpl;
import common.ManagerException;
import common.PedaggioException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import presentation.shared.Controller;


public class TollboothViewController extends Controller {
	
	@FXML
	private Label caselloLabel;
	@FXML 
	private Label autostradaLabel;
	@FXML
	private Label bigliettoLabel;
	@FXML
	private VBox dragTarget; 
	
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
                Dragboard dragBoard = event.getDragboard();
                boolean success = false;
                if (dragBoard.hasFiles()) {
                    bigliettoLabel.setText(dragBoard.getUrl().substring(6));
                    success = true;
                    bigliettoInserito = true;
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
	}
	
	@FXML
	public void calcolaPedaggio() {
	// TODO refactor
		if (bigliettoInserito) {
			File file = new File(bigliettoLabel.getText());
			String c = null, t = null;
			try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
				c = br.readLine();
				t = br.readLine();
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Biglietto non valido");
				alert.showAndWait();
				reset();
			}
			Casello e = null;
			Veicolo v = null;
			for(Casello ca: casello.getAutostrada().getCaselli()) {
				if(ca.getNome().equalsIgnoreCase(c)) {
					e = ca;
				}
			}
			try {
				v = new VeicoloMgr().load(t);
			} catch (ManagerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Pedaggio pedaggio = new PedaggioImpl();
			pedaggio.setEntrata(e);
			pedaggio.setUscita(casello);
			pedaggio.setVeicolo(v);
			try {
				pedaggio.calcolaPrezzo();

				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(TollDialogController.class.getResource("toll-dialog.fxml"));
				TollDialogController controller = new TollDialogController();
				controller.setPedaggio(pedaggio);
				controller.setStage(stage);
				loader.setController(controller);
				Pane tollDialog = loader.load();
				stage.setScene(new Scene(tollDialog));
				reset();
				stage.showAndWait();
			} catch (PedaggioException ex) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Impossibile calcolare il pedaggio");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void reset() {
		bigliettoInserito = false;
		bigliettoLabel.setText("Inserisci qui il biglietto...");
	}
}

package presentation.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import business.manager.CaselloMgr;
import business.manager.ManagerException;
import business.manager.PedaggioMgr;
import business.manager.VeicoloMgr;
import business.model.Casello;
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
	private BigliettoReader bigliettoReader;
	private class BigliettoReader {
		
		private BufferedReader br;
		
		public void insertTicket(String path) {
			try {
				br = new BufferedReader(new FileReader(new File(path)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void reset() {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			br = null;
		}
		
		public String[] readTicket() {
			String[] data = new String[2];
			try {
				data[0] = br.readLine();
				data[1] = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return data;
		}
	}
	
	
	public TollboothViewController(Casello casello) {
		this.bigliettoReader = new BigliettoReader();
		this.casello = casello;
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
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });
		
		dragTarget.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard dragBoard = event.getDragboard();
                if (dragBoard.hasFiles()) {
                    bigliettoLabel.setText(dragBoard.getUrl().substring(6));
                    bigliettoReader.insertTicket(dragBoard.getFiles().get(0).getAbsolutePath());
                }
                event.consume();
            }
        });
	}
	
	@FXML
	public void calcolaPedaggioHandle() {
		try {
			String[] ticketData = bigliettoReader.readTicket();
			String nomeCasello = ticketData[0];
			String targaVeicolo = ticketData[1];
			
			Casello entrata = CaselloMgr.getInstance().load(nomeCasello);
			Veicolo veicolo = VeicoloMgr.getInstance().load(targaVeicolo);
			Pedaggio pedaggio = PedaggioMgr.getInstance().calcolaPedaggio(entrata, this.casello, veicolo);
			
			try {
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(TollDialogController.class.getResource("toll-dialog.fxml"));
				TollDialogController controller = new TollDialogController();
				controller.setPedaggio(pedaggio);
				controller.setStage(stage);
				loader.setController(controller);
				Pane tollDialog = loader.load();
				stage.setScene(new Scene(tollDialog));
				stage.showAndWait();
		
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		} catch (ManagerException e) {
			e.printStackTrace();
			showAlert("Impossibile calcolare il pedaggio", "Il biglietto inserito non è valido");
		} finally {
			reset();
		}
	}
	
	public void reset() {
		bigliettoLabel.setText("Inserisci qui il biglietto...");
		bigliettoReader.reset();
	}
}

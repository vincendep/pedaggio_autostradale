package presentation.admin;

import business.manager.AutostradaMgr;
import business.model.Autostrada;
import business.model.Normativa;
import business.model.impl.AutostradaImpl;
import business.model.impl.Normativa2019Impl;
import common.ManagerException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.shared.Controller;

public class AdminEditHighwayDialogController extends Controller {
	@FXML
	private TextField nomeField;
	@FXML
	private TextField tariffaClasseAField;
	@FXML
	private TextField tariffaClasseBField;
	@FXML
	private TextField tariffaClasse3Field;
	@FXML
	private TextField tariffaClasse4Field;
	@FXML
	private TextField tariffaClasse5Field;
	
	private Autostrada autostrada;
	private Stage stage;
	
	public Autostrada getAutostrada() {
		return this.autostrada;
	}
	
	public void setAutostrada(Autostrada autostrada) {
		this.autostrada = autostrada;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	public void initialize() {
		Normativa normativaVigente = autostrada.getNormativaVigente();
		nomeField.setText(autostrada.getNome());
		tariffaClasseAField.setText(String.valueOf(normativaVigente.getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_A)));
		tariffaClasseBField.setText(String.valueOf(normativaVigente.getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_B)));
		tariffaClasse3Field.setText(String.valueOf(normativaVigente.getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_3)));
		tariffaClasse4Field.setText(String.valueOf(normativaVigente.getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_4)));
		tariffaClasse5Field.setText(String.valueOf(normativaVigente.getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_5)));
	}
	
	@FXML
	public void confermaHandle() {
		try {
			Autostrada a = new AutostradaImpl();
			Normativa n = (Normativa) Normativa.NORMATIVA_CORRENTE.newInstance();
			a.setNome(nomeField.getText());
			a.setNormativaVigente(n);
			n.setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_A, Float.parseFloat(tariffaClasseAField.getText()));
			n.setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_B, Float.parseFloat(tariffaClasseBField.getText()));
			n.setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_3, Float.parseFloat(tariffaClasse3Field.getText()));
			n.setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_4, Float.parseFloat(tariffaClasse4Field.getText()));
			n.setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_5, Float.parseFloat(tariffaClasse5Field.getText()));
		
			AutostradaMgr.getInstance().modify(autostrada.getNome(), a);
			
			autostrada.setNome(a.getNome());
			autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_A, n.getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_A));
			autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_B, n.getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_B));
			autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_3, n.getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_3));
			autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_4, n.getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_4));
			autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_5, n.getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_5));
			
		} catch (ManagerException e) {
			e.printStackTrace();
			showAlert("Impossibile modificare autostrada", "Dati inseriti non validi");
		} catch (Exception e) {
			e.printStackTrace();
			showAlert("Impossibile modificare autostrada", "Tariffa non valida");
		}
		stage.close();
	}
	
	@FXML
	public void cancellaHandle() {
		stage.close();
	}
}

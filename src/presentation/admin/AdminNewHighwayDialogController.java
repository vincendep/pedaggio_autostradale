package presentation.admin;

import java.util.List;

import business.AutostradaMgr;
import business.model.Autostrada;
import business.model.Normativa;
import business.model.impl.AutostradaImpl;
import business.model.impl.Normativa2019Impl;
import common.ManagerException;
import dao.DaoFactory;
import dao.DaoFactory.FactoryType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.shared.Controller;

public class AdminNewHighwayDialogController extends Controller {
	
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
	
	private List<Autostrada> autostrade;
	private Stage stage;
	
	public void setAutostrade(List<Autostrada> autostrade) {
		this.autostrade = autostrade;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	public void confermaHandle() {
		Autostrada autostrada = new AutostradaImpl();
		Normativa normativaVigente;
		try {
			normativaVigente = (Normativa) Autostrada.NORMATIVA_VIGENTE.newInstance();
			normativaVigente.setAutostrada(autostrada);
			autostrada.setNome(nomeField.getText());
			autostrada.setNormativaVigente(normativaVigente);
			normativaVigente.setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_A, Float.parseFloat(tariffaClasseAField.getText()));
			normativaVigente.setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_B, Float.parseFloat(tariffaClasseBField.getText()));
			normativaVigente.setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_3, Float.parseFloat(tariffaClasse3Field.getText()));
			normativaVigente.setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_4, Float.parseFloat(tariffaClasse4Field.getText()));
			normativaVigente.setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_5, Float.parseFloat(tariffaClasse5Field.getText()));
			
			new AutostradaMgr().add(autostrada);
			this.autostrade.add(autostrada);
			
		} catch (ManagerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		stage.close();
	}
	
	@FXML
	public void cancellaHandle() {
		stage.close();
	}
}

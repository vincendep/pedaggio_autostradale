package business.model;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Autostrada {

	private final StringProperty nome = new SimpleStringProperty();
	private final ObjectProperty<TariffaUnitaria> tariffaUnitaria = new SimpleObjectProperty<>();
	private final ObservableList<Casello> caselli = FXCollections.observableArrayList();
	
	public String getNome() {
		return this.nome.get();
	}
	
	public void setNome(String nome) {
		this.nome.set(nome);
	}
	
	public StringProperty nomeProperty() {
		return this.nome;
	}
	
	public TariffaUnitaria getTariffaUnitaria() {
		return this.tariffaUnitaria.get();
	}
	
	public void setTariffaUnitaria(TariffaUnitaria tariffaUnitaria) {
		this.tariffaUnitaria.set(tariffaUnitaria);
	}
	
	public ObjectProperty<TariffaUnitaria> tariffaUnitariaProperty() {
		return this.tariffaUnitaria;
	}
	
	public List<Casello> getCaselli() {
		return this.caselli;
	}
	
	public void setCaselli(List<Casello> caselli) {
		this.caselli.setAll(caselli);
	}
	
	public ObservableList<Casello> caselliProperty() {
		return this.caselli;
	}
}

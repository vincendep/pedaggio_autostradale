package business.model.impl;

import java.util.List;

import business.model.Autostrada;
import business.model.Casello;
import business.model.Normativa;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AutostradaImpl implements Autostrada {

	private final StringProperty nome = new SimpleStringProperty();
	private Normativa normativaVigente;
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
	
	public Normativa getNormativaVigente() {
		return this.normativaVigente;
	}
	
	public void setNormativaVigente(Normativa normativa) {
		this.normativaVigente = normativa;
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

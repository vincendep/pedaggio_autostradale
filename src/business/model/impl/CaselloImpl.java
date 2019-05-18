package business.model.impl;

import business.model.Autostrada;
import business.model.Casello;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CaselloImpl implements Casello {
	
	private final IntegerProperty id = new SimpleIntegerProperty();
	private final StringProperty nome = new SimpleStringProperty();
	private final IntegerProperty chilometro = new SimpleIntegerProperty();
	private Autostrada autostrada;
	
	public int getId() {
		return this.id.get();
	}
	
	public void setId(int id) {
		this.id.set(id);
	}
	
	public IntegerProperty idProperty() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome.get();
	}
	
	public void setNome(String nome) {
		this.nome.set(nome);
	}
	
	public StringProperty nomeProperty() {
		return this.nome;
	}
	
	public int getChilometro() {
		return this.chilometro.get();
	}

	public void setChilometro(int chilometro) {
		this.chilometro.set(chilometro);
	}
	
	public IntegerProperty chilometroProperty() {
		return this.chilometro;
	}
	
	public Autostrada getAutostrada() {
		return this.autostrada;
	}
	
	public void setAutostrada(Autostrada autostrada) {
		this.autostrada = autostrada;
	}
}

package business.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Casello {
	
	private final IntegerProperty id = new SimpleIntegerProperty();
	private final StringProperty nome = new SimpleStringProperty();
	private final IntegerProperty kilometro = new SimpleIntegerProperty();
	private final ObjectProperty<Autostrada> autostrada = new SimpleObjectProperty<>();
	
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
	
	public int getKilometro() {
		return this.kilometro.get();
	}

	public void setKilometro(int kilometro) {
		this.kilometro.set(kilometro);
	}
	
	public IntegerProperty kilometroProperty() {
		return this.kilometro;
	}
	
	public Autostrada getAutostrada() {
		return this.autostrada.get();
	}
	
	public void setAutostrada(Autostrada autostrada) {
		this.autostrada.set(autostrada);
	}
	
	public ObjectProperty<Autostrada> autostradaProperty() {
		return this.autostrada;
	}
}

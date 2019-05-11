/**
 * 
 */
package business.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author vincendep
 *
 */
public class Veicolo {
	
	private final StringProperty modello = new SimpleStringProperty();
	private final StringProperty marca = new SimpleStringProperty();
	private final StringProperty targa = new SimpleStringProperty();
	private final IntegerProperty numeroAssi = new SimpleIntegerProperty();
	private final IntegerProperty altezza = new SimpleIntegerProperty();
	private final IntegerProperty classeAmbientale = new SimpleIntegerProperty();
	public enum ClasseAmbientale{
		EURO_1,
		EURO_2,
		EURO_3,
		EURO_4,
		EURO_5,
		EURO_6
	}
	
	public String getModello() {
		return this.modello.get();
	}
	
	public void setModello(String modello) {
		this.modello.set(modello);
	}
	
	public StringProperty modelloProperty() {
		return this.modello;
	}
	
	public String getMarca() {
		return this.marca.get();
	}
	
	public void setMarca(String marca) {
		this.marca.set(marca);
	}
	
	public StringProperty marcaProperty() {
		return this.marca;
	}
	
	public String getTarga() {
		return this.targa.get();
	}
	
	public void setTarga(String targa) {
		this.targa.set(targa);
	}
	
	public StringProperty targaProperty() {
		return this.targa;
	}
	
	public int getNumeroAssi() {
		return this.numeroAssi.get();
	}
	
	public void setNumeroAssi(int numeroAssi) {
		this.numeroAssi.set(numeroAssi);
	}
	
	public IntegerProperty numeroAssiProperty() {
		return this.numeroAssi;
	}
	
	public int getAltezza() {
		return this.altezza.get();
	}
	
	public void setAltezza(int altezza) {
		this.altezza.set(altezza);
	}
	
	public IntegerProperty AltezzaProperty() {
		return this.altezza;
	}
	
	public ClasseAmbientale getClasseAmbientale() {
		return ClasseAmbientale.values()[this.classeAmbientale.get()];
	}
	
	public void setClasseAmbientale(ClasseAmbientale classeAmbientale) {
		this.classeAmbientale.set(classeAmbientale.ordinal());
	}
	
	public IntegerProperty classeAmbientaleProperty() {
		return this.classeAmbientale;
	}
}

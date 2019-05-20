/**
 * 
 */
package business.model.impl;

import business.model.Veicolo;

/**
 * @author vincendep
 *
 */
public class VeicoloImpl implements Veicolo {
	
	private int id;
	private String modello;
	private String marca;
	private String targa;
	private byte numeroAssi;
	private byte altezza;
	private ClasseAmbientale classeAmbientale;

	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getModello() {
		return this.modello;
	}
	
	public void setModello(String modello) {
		this.modello = modello;
	}
	
	public String getMarca() {
		return this.marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getTarga() {
		return this.targa;
	}
	
	public void setTarga(String targa) {
		this.targa = targa;
	}
	
	public byte getNumeroAssi() {
		return this.numeroAssi;
	}
	
	public void setNumeroAssi(byte numeroAssi) {
		this.numeroAssi = numeroAssi;
	}
	
	public byte getAltezza() {
		return this.altezza;
	}
	
	public void setAltezza(byte altezza) {
		this.altezza = altezza;
	}
	
	public ClasseAmbientale getClasseAmbientale() {
		return this.classeAmbientale;
	}
	
	public void setClasseAmbientale(ClasseAmbientale classeAmbientale) {
		this.classeAmbientale = classeAmbientale;
	}

	@Override
	public void setClasseAmbientale(short short1) {
		this.classeAmbientale = ClasseAmbientale.values()[short1 - 1];
	}
}

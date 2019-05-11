package business.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Pedaggio {
	
	private Casello entrata;
	private Casello uscita;
	private Veicolo veicolo;
	private DoubleProperty prezzo = new SimpleDoubleProperty();
	private Normativa normativa;
	
	
	public Casello getEntrata() {
		return entrata;
	}



	public void setEntrata(Casello entrata) {
		this.entrata = entrata;
	}



	public Casello getUscita() {
		return uscita;
	}



	public void setUscita(Casello uscita) {
		this.uscita = uscita;
	}



	public Veicolo getVeicolo() {
		return veicolo;
	}



	public void setVeicolo(Veicolo veicolo) {
		this.veicolo = veicolo;
	}



	public Normativa getNormativa() {
		return normativa;
	}



	public void setNormativa(Normativa normativa) {
		this.normativa = normativa;
	}

	public double getPrezzo() {
		return this.prezzo.doubleValue();
	}

	public void calcolaPrezzo() {
		this.prezzo.set(normativa.calcola(veicolo, entrata, uscita));
	}
}

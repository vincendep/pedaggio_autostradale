package business.model.impl;

import business.model.Casello;
import business.model.Pedaggio;
import business.model.Veicolo;
import common.PedaggioException;
import common.PercorsoException;


public class PedaggioImpl implements Pedaggio {
	
	private Casello entrata;
	private Casello uscita;
	private Veicolo veicolo;
	private float prezzo;
	
	
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

	
	public float getPrezzo() {
		return this.prezzo;
	}
	

	public void calcolaPrezzo() throws PedaggioException {
		try {
			this.prezzo = (entrata.getAutostrada().getNormativaVigente().calcolaPedaggio(veicolo, entrata, uscita));
		} catch (PercorsoException e) {
			throw new PedaggioException();
		}
	}
}

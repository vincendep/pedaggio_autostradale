package business.model.impl;

import business.model.Autostrada;
import business.model.Casello;
import business.model.Normativa;
import business.model.Veicolo;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class Normativa2019Impl implements Normativa {
	
	public static final int CLASSE_A = 1;
	public static final int CLASSE_B = 2;
	public static final int CLASSE_3 = 3;
	public static final int CLASSE_4 = 4;
	public static final int CLASSE_5 = 5;
	
	private FloatProperty tariffaClasseA;
	private FloatProperty tariffaClasseB;
	private FloatProperty tariffaClasse3;
	private FloatProperty tariffaClasse4;
	private FloatProperty tariffaClasse5;
	
	private Autostrada autostrada;
	
	
	public Normativa2019Impl() {
		tariffaClasseA = new SimpleFloatProperty();
		tariffaClasseB = new SimpleFloatProperty();
		tariffaClasse3 = new SimpleFloatProperty();
		tariffaClasse4 = new SimpleFloatProperty();
		tariffaClasse5 = new SimpleFloatProperty();
		
		autostrada = new AutostradaImpl();
	}

	@Override
	public Autostrada getAutostrada() {
		return this.autostrada;
	}
	
	@Override
	public void setAutostrada(Autostrada autostrada) {
		this.autostrada = autostrada;
	}
	
	@Override
	public float getTariffaClasseVeicolo(int classe) {
		switch (classe) {
			
			case CLASSE_A:
				return this.tariffaClasseA.get();
				
			case CLASSE_B:
				return this.tariffaClasseB.get();
				
			case CLASSE_3:
				return this.tariffaClasse3.get();
				
			case CLASSE_4:
				return this.tariffaClasse4.get();
				
			case CLASSE_5:
				return this.tariffaClasse5.get();
				
			default:
				return -1;
		}
	}
	
	
	@Override
	public void setTariffaClasseVeicolo(int classeVeicolo, float tariffa) {
		switch(classeVeicolo) {
			case 1: this.tariffaClasseA.set(tariffa);
					break;
			
			case 2: this.tariffaClasseB.set(tariffa);
					break;
			
			case 3: this.tariffaClasse3.set(tariffa);
					break;
					
			case 4: this.tariffaClasse4.set(tariffa);
					break;
					
			case 5: this.tariffaClasse5.set(tariffa);
					break;
					
			default: //TODO manage exception
		}
		
	}
	
	@Override
	public FloatProperty tariffaClasseVeicoloProperty(int classe) {
		switch (classe) {
			
			case CLASSE_A:
				return this.tariffaClasseA;
				
			case CLASSE_B:
				return this.tariffaClasseB;
				
			case CLASSE_3:
				return this.tariffaClasse3;
				
			case CLASSE_4:
				return this.tariffaClasse4;
				
			case CLASSE_5:
				return this.tariffaClasse5;
				
			default:
				return null;
		}
	}

	@Override
	public float getTariffaVeicolo(Veicolo veicolo) {
		switch(getClasseVeicolo(veicolo)) {
			case CLASSE_A:
				return this.tariffaClasseA.get();
				
			case CLASSE_B:
				return this.tariffaClasseB.get();
				
			case CLASSE_3:
				return this.tariffaClasse3.get();
				
			case CLASSE_4:
				return this.tariffaClasse4.get();
				
			case CLASSE_5:
				return this.tariffaClasse5.get();
				
			default:
				return -1;
		}
	}
	
	@Override
	public int getClasseVeicolo(Veicolo veicolo) {
		if (veicolo.getNumeroAssi() == 2 ) {
			if (veicolo.getAltezza() <= 130) {
				return CLASSE_A;
			} else {
				return CLASSE_B;
			}
		} else if (veicolo.getNumeroAssi() == 3) {
			return CLASSE_3;
		} else if (veicolo.getNumeroAssi() == 4) {
			return CLASSE_4;
		} else {
			return CLASSE_5;
		}
	}

	@Override
	public float calcolaPedaggio(Veicolo veicolo, Casello entrata, Casello uscita) {

		float chilometri = Math.abs(uscita.getChilometro() - entrata.getChilometro());
		float tariffa = getTariffaVeicolo(veicolo);
		float costo = tariffa * chilometri;
		float costoTassato = costo + costo * IVA;

		return Math.round(costoTassato * 10) / 10f;
	}

}
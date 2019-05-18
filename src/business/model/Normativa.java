package business.model;

import common.PercorsoException;
import javafx.beans.property.FloatProperty;

public interface Normativa {
	
	public static final float IVA = 0.22f;
	
	public Autostrada getAutostrada();
	public void setAutostrada(Autostrada autostrada);
	float getTariffaClasseVeicolo(int classe);
	void setTariffaClasseVeicolo(int classeVeicolo, float tariffa);
	float getTariffaVeicolo(Veicolo veicolo);
	float calcolaPedaggio(Veicolo veicolo, Casello entrata, Casello uscita) throws PercorsoException;
	int getClasseVeicolo(Veicolo veicolo);
	
	FloatProperty tariffaClasseProperty(int classe);
}

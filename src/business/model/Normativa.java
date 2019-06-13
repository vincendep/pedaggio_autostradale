 package business.model;

import business.model.impl.Normativa2019Impl;
import javafx.beans.property.FloatProperty;

public interface Normativa {
	
	/* In caso entri in vigore una nuova normativa, gli scenari sono due:
	 * 
	 * La nuova normativa prevede le stesse classi veicolari della precendente.
	 * 
	 * In questo caso è sufficiente estendere l'impementazione della precedente normativa, 
	 * sovrascrivendo i metodi (probabilmente "calcolaPedaggio") che necessitano di essere estesi.
	 *-------------------------------------------------------------------------------
	 * La nuova normativa definisce classi veicolari diverse dalla precedente.
	 * 
	 * In questo caso è necessario re-implementare l'interfaccia Normativa 
	 * e definire le nuove classi veicolari all'interno dell'implementazione.
	 * 
	 * NB: le viste (amministrative) che mostravano informazioni sulle
	 *     classi veicolari precedenti dovranno essere modificate.
	 *-------------------------------------------------------------------------------
	 * In entrambi i casi, specificare infine la nuova NORMATIVA_CORRENTE
	 */
	
	@SuppressWarnings("rawtypes")
	public static Class NORMATIVA_CORRENTE = Normativa2019Impl.class;
	public static final float IVA = 0.22f;
	
	Autostrada getAutostrada();
	void setAutostrada(Autostrada autostrada);
	float getTariffaClasseVeicolo(int classe);
	void setTariffaClasseVeicolo(int classeVeicolo, float tariffa);
	FloatProperty tariffaClasseVeicoloProperty(int classe);
	float getTariffaVeicolo(Veicolo veicolo);
	Pedaggio calcolaPedaggio(Veicolo veicolo, Casello entrata, Casello uscita);
	int getClasseVeicolo(Veicolo veicolo);
	
}

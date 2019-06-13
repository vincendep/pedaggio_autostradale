package business.model;

public interface Pedaggio {
	
	public Veicolo getVeicolo();
	public void setVeicolo(Veicolo veicolo);
	public Casello getEntrata();
	public void setEntrata(Casello entrata);
	public Casello getUscita();
	public void setUscita(Casello uscita);
	public float getPrezzo();
	public void setPrezzo(float prezzo);
}

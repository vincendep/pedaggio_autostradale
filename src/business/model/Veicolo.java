package business.model;

public interface Veicolo {
	
	public enum ClasseAmbientale {
		EURO_1,
		EURO_2,
		EURO_3,
		EURO_4,
		EURO_5,
		EURO_6,
	}
	
	public int getId();
	public void setId(int id);
	public String getModello();
	public void setModello(String modello);
	public String getMarca();
	public void setMarca(String marca);
	public String getTarga();
	public void setTarga(String targa);
	public byte getNumeroAssi();
	public void setNumeroAssi(byte numeroAssi);
	public byte getAltezza();
	public void setAltezza(byte altezza);
	public ClasseAmbientale getClasseAmbientale();
	public void setClasseAmbientale(ClasseAmbientale classeAmbientale);
}

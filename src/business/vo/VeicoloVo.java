package business.vo;

public class VeicoloVo {
	private int id;
	private String nome;
	private byte numeroAssi;
	private byte classeAmbientale;
	private int idCasaProduttrice;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public byte getNumeroAssi() {
		return numeroAssi;
	}
	public void setNumeroAssi(byte numeroAssi) {
		this.numeroAssi = numeroAssi;
	}
	public byte getClasseAmbientale() {
		return classeAmbientale;
	}
	public void setClasseAmbientale(byte classeAmbientale) {
		this.classeAmbientale = classeAmbientale;
	}
	public int getIdCasaProduttrice() {
		return idCasaProduttrice;
	}
	public void setIdCasaProduttrice(int idCasaProduttrice) {
		this.idCasaProduttrice = idCasaProduttrice;
	}
}

package business.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public interface Casello {

	public int getId();
	public void setId(int id);
	public IntegerProperty idProperty();
	public String getNome();
	public void setNome(String nome);
	public StringProperty nomeProperty();
	public int getChilometro();
	public void setChilometro(int chilometro);
	public IntegerProperty chilometroProperty();
	public Autostrada getAutostrada();
	public void setAutostrada(Autostrada autostrada);
}

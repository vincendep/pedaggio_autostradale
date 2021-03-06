package business.model;

import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;


public interface Autostrada {
	
	public String getNome();
	public void setNome(String nome);
	public StringProperty nomeProperty();
	public List<Casello> getCaselli();
	public void setCaselli(List<Casello> caselli);
	public ObservableList<Casello> caselliProperty();
	public Normativa getNormativaVigente();
	public void setNormativaVigente(Normativa normativa);
	public boolean equals(Object o);
}

package business.dao.common;

import java.util.List;

import business.model.Autostrada;

public interface AutostradaDao {

	List<Autostrada> getAll();
	Autostrada getByName(String nome);
	void store(Autostrada autostrada);
	void update(Autostrada autostrada);
	void delete(Autostrada autostrada);
}

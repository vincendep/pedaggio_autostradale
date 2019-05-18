package dao;

import java.util.List;

import business.model.Autostrada;
import business.model.Casello;


public interface CaselloDao {

	List<Casello> getAll(Autostrada autostrada);
	Casello getByName(String nome);
	Casello getById(int id);
	void update(Casello casello);
	void store(Casello casello);
	void delete(Casello casello);
}

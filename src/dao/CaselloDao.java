package dao;

import java.util.List;

import business.model.Autostrada;
import business.model.Casello;
import common.DaoException;


public interface CaselloDao {

	List<Casello> loadAll(Autostrada autostrada) throws DaoException;
	Casello loadByName(String nome) throws DaoException;
	Casello loadById(int id) throws DaoException;
	void update(String nome, Casello casello) throws DaoException;
	void store(Casello casello) throws DaoException;
	void delete(Casello casello) throws DaoException;
}

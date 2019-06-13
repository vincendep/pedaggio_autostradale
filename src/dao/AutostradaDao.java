package dao;

import java.util.List;

import business.model.Autostrada;

public interface AutostradaDao {

	int getId(Autostrada autostrada) throws DaoException;
	List<Autostrada> loadAll() throws DaoException;
	void store(Autostrada autostrada) throws DaoException;
	void update(String nome, Autostrada autostrada) throws DaoException;
	void delete(Autostrada autostrada) throws DaoException;
}

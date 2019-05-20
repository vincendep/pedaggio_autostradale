package dao;

import business.model.Veicolo;
import common.DaoException;

public interface VeicoloDao {

	Veicolo getByTarga(String targa) throws DaoException;
}

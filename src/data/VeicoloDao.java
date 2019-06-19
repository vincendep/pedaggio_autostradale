package data;

import business.model.Veicolo;

public interface VeicoloDao {

	Veicolo getByTarga(String targa) throws DaoException;
}

package business;

import business.model.Veicolo;
import common.DaoException;
import common.ManagerException;
import dao.DaoFactory;
import dao.DaoFactory.FactoryType;

public class VeicoloMgr {
	
	private DaoFactory factory = DaoFactory.getDaoFactory(FactoryType.MYSQL);
	
	public Veicolo load(String targa) throws ManagerException {
		try {
			return factory.getVeicoloDao().getByTarga(targa);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}
}

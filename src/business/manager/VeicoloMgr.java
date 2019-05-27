package business.manager;

import business.model.Veicolo;
import common.DaoException;
import common.ManagerException;
import dao.DaoFactory;
import dao.DaoFactory.FactoryType;

/*
 * Manager implementing business logic regarding vehicles
 */
public class VeicoloMgr {
	
	private DaoFactory factory = DaoFactory.getDaoFactory(FactoryType.MYSQL);
	
	
	private VeicoloMgr() {}
	
	public static VeicoloMgr getInstance() {
		return new VeicoloMgr();
	}
	
	public Veicolo load(String targa) throws ManagerException {
		try {
			return factory.getVeicoloDao().getByTarga(targa);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}
}

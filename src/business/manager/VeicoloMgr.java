package business.manager;

import business.model.Veicolo;
import data.DaoException;
import data.DaoFactory;
import data.DaoFactory.FactoryType;

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
			Veicolo v = factory.getVeicoloDao().getByTarga(targa);
			if (v.equals(null)) {
				throw new ManagerException();
			}
			return v;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}
}

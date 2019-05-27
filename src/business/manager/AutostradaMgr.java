package business.manager;

import java.util.List;

import business.model.Autostrada;
import common.DaoException;
import common.ManagerException;
import dao.DaoFactory;
import dao.DaoFactory.FactoryType;

/*
 * Manager implementing business logic regarding highways
 * @ author vincendep
 */
public class AutostradaMgr {
	
	private DaoFactory factory = DaoFactory.getDaoFactory(FactoryType.MYSQL);
	
	
	private  AutostradaMgr() {}
	
	public static AutostradaMgr getInstance() {
		return new AutostradaMgr();
	}
	
	/*
	 * Load all highways from the database
	 * @throws ManageException
	 * @return All highways stored in the database
	 */
	public List<Autostrada> loadAll() throws ManagerException {
		try {
			return factory.getAutostradaDao().loadAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}
	
	/*
	 * Save an highway in the database
	 * @param the highway to be saved
	 * @throws ManagerException
	 */
	public void save(Autostrada a) throws ManagerException {
		try {
			factory.getAutostradaDao().store(a);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}
	
	/*
	 * Remove an highway from the database
	 * @param the highway to be removed
	 * @throws ManagerException
	 */
	public void remove(Autostrada a) throws ManagerException {
		try {
			factory.getAutostradaDao().delete(a);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}
	
	/*
	 * Modify information about an highway
	 * @param name of the highway
	 * @param new highway data
	 */
	public void modify(String nome, Autostrada a) throws ManagerException {
		try {
			factory.getAutostradaDao().update(nome, a);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}
}

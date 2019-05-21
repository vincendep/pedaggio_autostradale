package business.manager;

import java.util.List;

import business.model.Autostrada;
import common.DaoException;
import common.ManagerException;
import dao.DaoFactory;
import dao.DaoFactory.FactoryType;

public class AutostradaMgr {
	
	private DaoFactory factory = DaoFactory.getDaoFactory(FactoryType.MYSQL);
	
	
	private  AutostradaMgr() {}
	
	public static AutostradaMgr getInstance() {
		return new AutostradaMgr();
	}
			
	public List<Autostrada> loadAll() throws ManagerException {
		try {
			return factory.getAutostradaDao().loadAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}
	
	public void save(Autostrada a) throws ManagerException {
		try {
			factory.getAutostradaDao().store(a);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}
	
	public void remove(Autostrada a) throws ManagerException {
		try {
			factory.getAutostradaDao().delete(a);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}
	
	public void modify(String nome, Autostrada a) throws ManagerException {
		try {
			factory.getAutostradaDao().update(nome, a);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}
}

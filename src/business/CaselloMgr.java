package business;

import java.util.List;

import business.model.Autostrada;
import business.model.Casello;
import common.DaoException;
import common.ManagerException;
import dao.DaoFactory;
import dao.DaoFactory.FactoryType;

public class CaselloMgr {
	
	private DaoFactory factory = DaoFactory.getDaoFactory(FactoryType.MYSQL);
	
	public List<Casello> getAll(Autostrada a) throws ManagerException {
		try {
			return factory.getCaselloDao().loadAll(a);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}
	
	public void add(Casello c) throws ManagerException {
		try {
			factory.getCaselloDao().store(c);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}
	
	public void remove(Casello c) throws ManagerException {
		try {
			factory.getCaselloDao().delete(c);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}
	
	public void modify(String nome, Casello c) throws ManagerException {
		try {
			factory.getCaselloDao().update(nome, c);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}

}

package business.manager;

import java.util.List;

import business.model.Autostrada;
import business.model.Casello;
import dao.DaoException;
import dao.DaoFactory;
import dao.DaoFactory.FactoryType;

/*
 * Manager implementing business logic regarding tollbooths
 * @author vincendep
 */
public class CaselloMgr {
	
	private DaoFactory factory = DaoFactory.getDaoFactory(FactoryType.MYSQL);
	
	
	private CaselloMgr() {}
	
	public static CaselloMgr getInstance() {
		return new CaselloMgr();
	}
	
	public Casello load(String nome) throws ManagerException {
		List<Autostrada> autostrade = AutostradaMgr.getInstance().loadAll();
		for (Autostrada a: autostrade) {
			List<Casello> caselli = loadAll(a);
			for (Casello c: caselli) {
				if (c.getNome().equalsIgnoreCase(nome)) {
					return c;
				}
			}
		}
		return null;
	}
	
	public List<Casello> loadAll(Autostrada a) throws ManagerException {
		try {
			return factory.getCaselloDao().loadAll(a);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ManagerException();
		}
	}
	
	public void save(Casello c) throws ManagerException {
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

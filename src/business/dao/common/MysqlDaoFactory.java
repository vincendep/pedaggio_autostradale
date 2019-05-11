package business.dao.common;

import business.dao.AutostradaMysqlDao;
import business.dao.CaselloMysqlDao;
import business.dao.VeicoloMysqlDao;

public class MysqlDaoFactory extends DaoFactory {
	
	private static MysqlDaoFactory instance;
	
	private MysqlDaoFactory() {};
	
	public static MysqlDaoFactory getInstance() {
		if (instance == null) {
			instance = new MysqlDaoFactory();
		}
		return instance;
	}
	
	public Dao createDao(Tipo dao) {
		if (dao == Tipo.AUTOSTRADA) {
			return new AutostradaMysqlDao();
		} else if (dao == Tipo.CASELLO) {
			return new CaselloMysqlDao();
		} else {
			return new VeicoloMysqlDao();
		}
	}

}

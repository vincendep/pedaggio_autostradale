package business.dao.common;

public abstract class DaoFactory {
	
	private static DaoFactory instance;
	
	public enum FactoryType {
		MYSQL
	}
	
	protected enum Tipo {
		AUTOSTRADA,
		CASELLO,
		VEICOLO
	}
	
	protected abstract Dao createDao(Tipo dao);
	
	public static DaoFactory getDaoFactory(FactoryType factoryType) {
		if (factoryType == FactoryType.MYSQL) {
			return MysqlDaoFactory.getInstance();
		}
		return null;
	}
	
	public AutostradaDao getAutostradaDao() {
		return (AutostradaDao) createDao(Tipo.AUTOSTRADA);
	}
	
	public CaselloDao getCaselloDao() {
		return (CaselloDao) createDao(Tipo.CASELLO);
	}
	
	public VeicoloDao getVeicoloDao() {
		return (VeicoloDao) createDao(Tipo.VEICOLO);
	}
}

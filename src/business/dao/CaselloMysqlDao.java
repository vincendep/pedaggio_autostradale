package business.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import business.dao.common.CaselloDao;
import business.dao.common.MysqlDao;
import business.model.Autostrada;
import business.model.Casello;

public class CaselloMysqlDao extends MysqlDao implements CaselloDao {

	@Override
	public List<Casello> getAll(Autostrada autostrada) {
		//TODO recuperare id autostrada
		Connection connection = connect();
		try {
			Statement s = connection.createStatement();
			s.executeQuery("SELECT * FROM tollbooth WHERE highway_id = " + );
		}
	}

	@Override
	public Casello getByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Casello getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Casello casello) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void store(Casello casello) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Casello casello) {
		// TODO Auto-generated method stub
		
	}

}

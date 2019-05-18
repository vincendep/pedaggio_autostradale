package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import business.model.Autostrada;
import business.model.Casello;
import business.model.impl.AutostradaImpl;
import business.model.impl.CaselloImpl;
import dao.DaoFactory.FactoryType;

class CaselloMysqlDao extends MysqlDao implements CaselloDao {
	//TODO
	protected CaselloMysqlDao() {}

	@Override
	public List<Casello> getAll(Autostrada autostrada) {
		List<Casello> caselli = new ArrayList<>();
		long id_autostrada = DaoFactory.getDaoFactory(FactoryType.MYSQL).getAutostradaDao().getId(autostrada.getNome());
		
		Connection connection = connect();
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM tollbooth WHERE highway_id = " + id_autostrada);
			while (rs.next()) {
				Casello casello = new CaselloImpl();
				casello.setId(rs.getInt("id"));
				casello.setNome(rs.getString("nome"));
				casello.setChilometro(rs.getShort("chilometro"));
				casello.setAutostrada(autostrada);
				caselli.add(casello);
				
				closeConnection(connection, s, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return caselli;
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

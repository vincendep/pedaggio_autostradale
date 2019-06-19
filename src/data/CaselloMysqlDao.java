package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import business.model.Autostrada;
import business.model.Casello;

import business.model.impl.CaselloImpl;
import data.DaoFactory.FactoryType;

class CaselloMysqlDao extends MysqlDao implements CaselloDao {
	
	public static final String INSERT_QUERY = "INSERT INTO casello (id, nome, chilometro, id_autostrada) VALUES (?, ?, ?, ?)";
	public static final String DELETE_QUERY = "DELETE FROM casello WHERE id = ?";
	private final static String UPDATE_QUERY = "UPDATE casello SET id = ?, nome = ?, chilometro = ?  WHERE nome = ?";
	
	
	protected CaselloMysqlDao() {}

	@Override
	public List<Casello> loadAll(Autostrada autostrada) throws DaoException {
		List<Casello> caselli = new ArrayList<>();
		int idAutostrada = DaoFactory.getDaoFactory(FactoryType.MYSQL).getAutostradaDao().getId(autostrada);
		
		Connection connection = connect();
		Statement s = null;
		ResultSet rs = null;
		try {
			s = connection.createStatement();
			rs = s.executeQuery("SELECT * FROM casello WHERE id_autostrada = " + idAutostrada);
			while (rs.next()) {
				Casello casello = new CaselloImpl();
				casello.setId(rs.getInt("id"));
				casello.setNome(rs.getString("nome"));
				casello.setChilometro(rs.getShort("chilometro"));
				casello.setAutostrada(autostrada);
				caselli.add(casello);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} finally {
			closeConnection(connection, s, rs);
		}
		return caselli;
	}

	@Override
	public Casello loadByName(String nome) throws DaoException {
		Casello casello = new CaselloImpl();
		
		Connection c = connect();
		Statement s = null;
		ResultSet rs = null;
		
		try {
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM casello WHERE nome = '" + nome + "'");
			while (rs.next()) {
				casello.setId(rs.getInt("id"));
				casello.setNome(rs.getString("nome"));
				casello.setChilometro(rs.getInt("chilometro"));
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} finally {
			closeConnection(c, s, rs);
		}
		return casello;
	}

	@Override
	public Casello loadById(int id) throws DaoException {
		Casello casello = new CaselloImpl();
		
		Connection c = connect();
		Statement s = null;
		ResultSet rs = null;
		
		try {
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM casello WHERE id = '" + id + "'");
			while (rs.next()) {
				casello.setId(rs.getInt("id"));
				casello.setNome(rs.getString("nome"));
				casello.setChilometro(rs.getInt("chilometro"));
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} finally {
			closeConnection(c, s, rs);
		}
		return casello;
	}

	@Override
	public void update(String nome, Casello casello) throws DaoException {
		Connection c = connect();
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(UPDATE_QUERY);
			ps.setInt(1, casello.getId());
			ps.setString(2, casello.getNome());
			ps.setInt(3, casello.getChilometro());
			ps.setString(4, nome);
			
			ps.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} finally {
			closeConnection(c, ps);
		}
		
	}

	@Override
	public void store(Casello casello) throws DaoException {
		Connection c = connect();
		PreparedStatement ps = null;
		
		try {
			ps = c.prepareStatement(INSERT_QUERY);
			ps.setInt(1, casello.getId());
			ps.setString(2, casello.getNome());
			ps.setInt(3, casello.getChilometro());
			ps.setInt(4, new AutostradaMysqlDao().getId(casello.getAutostrada()));
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} finally {
			closeConnection(c, ps);
		}
	}

	@Override
	public void delete(Casello casello) throws DaoException {
		Connection c = connect();
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(DELETE_QUERY);
			ps.setInt(1, casello.getId());
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} finally {
			closeConnection(c, ps);
		}
		
	}

}

package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

import business.model.Autostrada;
import business.model.Normativa;
import business.model.impl.AutostradaImpl;
import business.model.impl.Normativa2019Impl;
import common.DaoException;
import dao.DaoFactory.FactoryType;

class AutostradaMysqlDao extends MysqlDao implements AutostradaDao {
	
	private final static String INSERT_QUERY = "INSERT INTO autostrada (nome, tariffa_classe_a, tariffa_classe_b, tariffa_classe_3, tariffa_classe_4, tariffa_classe_5) VALUES (?, ?, ?, ?, ?, ?)";
	private final static String UPDATE_QUERY = "UPDATE autostrada SET nome = ?, tariffa_classe_a = ?, tariffa_classe_b = ?, tariffa_classe_3 = ?, tariffa_classe_4 = ?, tariffa_classe_5 = ? WHERE nome = ?";
	private final static String DELETE_QUERY = "DELETE FROM autostrada WHERE id = ?";
	
	protected AutostradaMysqlDao() {}
	
	public int getId(Autostrada autostrada) throws DaoException{
		Connection connection = connect();
		Statement s = null;
		ResultSet rs = null;
		int id = 0;
		
		try {
			s = connection.createStatement();
			rs = s.executeQuery("SELECT * FROM autostrada WHERE nome = '" + autostrada.getNome() + "'");
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} finally {
			closeConnection(connection, s, rs);
		}
		return id;
	}
	
	@Override
	public List<Autostrada> loadAll() throws DaoException {
		List<Autostrada> autostrade = new ArrayList<>();
		Connection connection = connect();
		Statement s = null;
		ResultSet rs = null;
		
		try {
			s = connection.createStatement();
			rs = s.executeQuery("SELECT * FROM autostrada");
			while (rs.next()) {
				Autostrada autostrada = new AutostradaImpl();
				autostrada.setNome(rs.getString("nome"));
				autostrada.setNormativaVigente((Normativa) Autostrada.NORMATIVA_VIGENTE.newInstance());
				autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_A, (rs.getFloat("tariffa_classe_a")));
				autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_B, (rs.getFloat("tariffa_classe_b")));
				autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_3, (rs.getFloat("tariffa_classe_3")));
				autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_4, (rs.getFloat("tariffa_classe_4")));
				autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_5, (rs.getFloat("tariffa_classe_5")));
				autostrada.setCaselli(DaoFactory.getDaoFactory(FactoryType.MYSQL).getCaselloDao().loadAll(autostrada));
				autostrade.add(autostrada);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, s, rs);
		}
		return autostrade;
	}


	@Override
	public void store(Autostrada autostrada) throws DaoException {
		Connection connection = connect();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(INSERT_QUERY);
			ps.setString(1, autostrada.getNome());
			ps.setFloat(2, autostrada.getNormativaVigente().getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_A));
			ps.setFloat(3, autostrada.getNormativaVigente().getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_B));
			ps.setFloat(4, autostrada.getNormativaVigente().getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_3));
			ps.setFloat(5, autostrada.getNormativaVigente().getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_4));
			ps.setFloat(6, autostrada.getNormativaVigente().getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_5));
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} finally {
			closeConnection(connection, ps);
		}
	}	

	@Override
	public void update(String nome, Autostrada autostrada) throws DaoException {
		Connection connection = connect();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(UPDATE_QUERY);
			ps.setString(1, autostrada.getNome());
			ps.setFloat(2, autostrada.getNormativaVigente().getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_A));
			ps.setFloat(3, autostrada.getNormativaVigente().getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_B));
			ps.setFloat(4, autostrada.getNormativaVigente().getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_3));
			ps.setFloat(5, autostrada.getNormativaVigente().getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_4));
			ps.setFloat(6, autostrada.getNormativaVigente().getTariffaClasseVeicolo(Normativa2019Impl.CLASSE_5));
			ps.setString(7, nome);
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} finally {
			closeConnection(connection, ps);
		}
		
	}

	@Override
	public void delete(Autostrada autostrada) throws DaoException {
		Connection connection = connect();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(DELETE_QUERY);
			ps.setString(1, autostrada.getNome());
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} finally {
			closeConnection(connection, ps);
		}
		
	}

}

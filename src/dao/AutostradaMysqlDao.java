package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import business.model.Autostrada;
import business.model.Normativa;
import business.model.impl.AutostradaImpl;
import business.model.impl.Normativa2019Impl;
import dao.DaoFactory.FactoryType;

class AutostradaMysqlDao extends MysqlDao implements AutostradaDao {
// TODO
	protected AutostradaMysqlDao() {}
	
	public long getId(String nome) {
		Connection connection = connect();
		long id = 0;
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("SELECT id FROM autostrada where nome=" + nome);
			while (rs.next()) {
				id = rs.getLong("id");
			}
			closeConnection(connection, s, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	@Override
	public List<Autostrada> getAll() {
		Connection connection = connect();
		List<Autostrada> autostrade = new ArrayList<>();
		
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM autostrada");
			while (rs.next()) {
				Autostrada autostrada = new AutostradaImpl();
				autostrada.setNome(rs.getString("nome"));
				autostrada.setNormativaVigente((Normativa) Autostrada.NORMATIVA_VIGENTE.newInstance());
				autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_A, (rs.getFloat("tariffa_classe_a")));
				autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_B, (rs.getFloat("tariffa_classe_b")));
				autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_3, (rs.getFloat("tariffa_classe_3")));
				autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_4, (rs.getFloat("tariffa_classe_4")));
				autostrada.getNormativaVigente().setTariffaClasseVeicolo(Normativa2019Impl.CLASSE_5, (rs.getFloat("tariffa_classe_5")));
				autostrada.setCaselli(DaoFactory.getDaoFactory(FactoryType.MYSQL).getCaselloDao().getAll(autostrada));
				autostrade.add(autostrada);
			
				closeConnection(connection, s, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return autostrade;
	}

	@Override
	public Autostrada getByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void store(Autostrada autostrada) {
		
	}	

	@Override
	public void update(Autostrada autostrada) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Autostrada autostrada) {
		// TODO Auto-generated method stub
		
	}

}

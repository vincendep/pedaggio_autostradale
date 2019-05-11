package business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import business.dao.common.AutostradaDao;
import business.dao.common.MysqlDao;
import business.model.Autostrada;

public class AutostradaMysqlDao extends MysqlDao implements AutostradaDao {
	
	private static String INSERT_STMT = "INSERT INTO highway(name, type) VALUES (?, ?)";
	private static String QUERY_STRING = "SELECT * FROM highway WHERE  name = ?";

	@Override
	public List<Autostrada> getAll() {
		Connection connection = connect();
		List<Autostrada> autostrade = new ArrayList<>();
		
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM highway");
			while (rs.next()) {
				Autostrada autostrada = new Autostrada();
				autostrada.setNome(rs.getString("name"));
				autostrade.add(autostrada);
			}
			return autostrade;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Autostrada getByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void store(Autostrada autostrada) {
		Connection conn = connect();
		try {
			PreparedStatement ps = conn.prepareStatement(INSERT_STMT);
			ps.setString(1, autostrada.getNome());
			ps.setString(2, "lowland");
			ps.execute();
			
			closeConnectio(conn, ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

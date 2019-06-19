package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import business.model.Veicolo;
import business.model.impl.VeicoloImpl;


class VeicoloMysqlDao extends MysqlDao implements VeicoloDao {

	@Override
	public Veicolo getByTarga(String targa) throws DaoException {
		Veicolo v = null;
		Connection c = connect();
		Statement s = null;
		ResultSet rs = null;
		try {
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM veicolo WHERE targa = '" + targa + "'");
			while (rs.next()) {
				v = new VeicoloImpl();
				v.setId(rs.getInt("id"));
				v.setModello(rs.getString("modello"));
				v.setMarca(rs.getString("marca"));
				v.setAltezza(rs.getByte("altezza"));
				v.setClasseAmbientale(rs.getShort("classe_ambiente"));
				v.setNumeroAssi(rs.getByte("numero_assi"));
				v.setTarga(rs.getString("targa"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} finally {
			closeConnection(c, s, rs);
		}
		return v;
	}

}

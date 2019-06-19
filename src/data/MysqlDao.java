package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class MysqlDao extends Dao {
	
	private static String URL = "jdbc:mysql://localhost/pedaggio_autostradale?useSSL=false";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    
	
	protected Connection connect() {
		Connection conn = null;

        try  {
            Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(URL,  "root",  "");

        } catch (Exception e)  {
        	e.printStackTrace();
        }
        return conn;
	}
	
	protected void closeConnection(Connection connection, Statement statement) {
		try {
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void closeConnection(Connection connection, Statement statement,ResultSet resultSet) {
		try {
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

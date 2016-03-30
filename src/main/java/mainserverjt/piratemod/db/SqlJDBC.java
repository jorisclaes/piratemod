package mainserverjt.piratemod.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlJDBC {

	private static Connection c = null;
	public static String url, dbNaam, username, passwoord;
	
	/**
	 * gaat connectie leggen met de sql server
	 * VERGEET JE CONNECTIE NIET TE SLUITEN
	 */
	public void maakConnectie(){
		try {
			Class.forName("com.mysql.jdbc.Drver");
			c = DriverManager.getConnection("JDBC:mysql:" + url + dbNaam, username, passwoord);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * gaat een qry uitvoeren
	 * @param sql de sql selectie als String
	 * @return 
	 * 		als gelukt is returnt die een ResultSet van de data
	 * 		en anders een null
	 */
	public ResultSet voerQryUit(String sql){
		Statement stm;
		try {
			stm = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			return stm.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * gaat een update uit voeren in de DB
	 * @param sql de sql update selectie als String
	 * @return 
	 * 		returnt -1 als het niet gelukt is en
	 * 		returnt X aantal rijen als int als het wel geluct is
	 * 
	 * als je 5 rijen aanpast zou die 5 moetten returnen
	 * zo niet heeft die niet alle rijen gedaan
	 */
	public int update(String sql){
		int cont = -1;
		try{
			Statement stm = c.createStatement();
			cont = stm.executeUpdate(sql);
			stm.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return cont;
	}
	
	/**
	 * gaat de connectie sluiten met de sql server
	 */
	public void sluitConnectie(){
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

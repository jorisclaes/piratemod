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
	 * @throws SQLException 
	 */
	public static void maakConnectie() throws SQLException{
		c = DriverManager.getConnection("JDBC:mysql:" + url + dbNaam, username, passwoord);
	}
	
	/**
	 * gaat een qry uitvoeren
	 * @param sql de sql selectie als String
	 * @return 
	 * 		altijd da uit komst van da sql
	 * @throws SQLException 
	 */
	public static ResultSet voerQryUit(String sql) throws SQLException{
		Statement stm = null;
		stm = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = null;
		rs = stm.executeQuery(sql);
		return rs;
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
	 * @throws SQLException 
	 */
	public static int voerUpdateUit(String sql) throws SQLException{
		int cont = -1;
		Statement stm = c.createStatement();
		cont = stm.executeUpdate(sql);
		stm.close();
		return cont;
	}
	
	/**
	 * gaat de connectie sluiten met de sql server
	 * @throws SQLException 
	 */
	public static void sluitConnectie() throws SQLException{
		c.close();
	}
}

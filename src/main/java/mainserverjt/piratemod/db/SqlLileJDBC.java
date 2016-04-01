package mainserverjt.piratemod.db;

import java.sql.*;

public class SqlLileJDBC {

	private static Connection c = null;
	
	/**
	 * gaat connectie leggen met de sql file
	 * VERGEET JE CONNECTIE NIET TE SLUITEN
	 */
	public static void maakConnectie(){
		try {
			Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite:" + FileHandler.getPath() + "PirateMod.db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
	public static ResultSet voerQryUit(String SQL){
		Statement stm;
		try {
			stm = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			return stm.executeQuery(SQL);
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
	public static int voerUpdateUit(String sql){
		int cont = 0;
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
	 * gaat de connectie sluiten met de sql file
	 */
	public static void sluitConnectie(){
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

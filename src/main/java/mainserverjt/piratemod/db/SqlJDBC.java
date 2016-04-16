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
	 * @throws SQLException obj met error in
	 */
	public static void maakConnectie() throws SQLException{
		try {
            //MySql db
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            c = DriverManager.getConnection("jdbc:mysql://" + url, username, passwoord);
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }catch(InstantiationException e){
        	e.printStackTrace();
        }catch(IllegalAccessException e){
        	e.printStackTrace();
        }catch(SQLException e){
        	e.printStackTrace();
        }
	}
	
	/**
	 * gaat een qry uitvoeren
	 * @param sql de sql selectie als String
	 * @return altijd de uit komst van da sql
	 */
	public static ResultSet voerQryUit(String sql){
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
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
	 */
	public static int voerUpdateUit(String sql){
		int cont = -1;
		Statement stm;
		try {
			stm = c.createStatement();
			cont = stm.executeUpdate(sql);
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return cont;
	}
	
	/**
	 * gaat de connectie sluiten met de sql server
	 * @throws SQLException obj met error in
	 */
	public static void sluitConnectie() throws SQLException{
		c.close();
	}
}

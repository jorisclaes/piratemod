package mainserverjt.piratemod.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.crew.Crew;
import mainserverjt.piratemod.crew.Pirate;

public class CrewHandler extends FileHandler {

	private String fileName;

	public CrewHandler(Main main) {
		super(main);
	}

	/**
	 * gaat alle data uit lezen van de DB
	 */
	public void readData() {
		if (super.isGebruiktDB()) {
			// gebruikt db server
			try {
				SqlJDBC.maakConnectie();
				leesDB("server");
				SqlJDBC.sluitConnectie();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//gebruikt db file
			SqlLileJDBC.maakConnectie();
			leesDB("file");
			SqlLileJDBC.sluitConnectie();
		}
	}

	/**
	 * gaat alle data weg schrijven naar de DB
	 */
	public void saveData(){
		if(super.isGebruiktDB()){
			//gebruikt db server
			try {
				SqlJDBC.maakConnectie();
				schrijfDB("server");
				SqlJDBC.sluitConnectie();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//gebruikt db file
			SqlLileJDBC.maakConnectie();
			schrijfDB("file");
			SqlLileJDBC.sluitConnectie();
		}
	}
	
	private void schrijfDB(String string) {
		try{
			for(Crew crew : super.getMain().getBestaandeCrews()){
				boolean gevonden = false;
				ResultSet rs = null;
				if(string.equals("server")){
					SqlJDBC.maakConnectie();
					rs = SqlJDBC.voerQryUit("select * from crew where id = " + crew.getId());
				}else{
					SqlLileJDBC.maakConnectie();
					rs = SqlLileJDBC.voerQryUit("select * from crew where id = " + crew.getId());
				}
				if(rs.last()){
					//bestaad
					rs.updateInt("rank", crew.getRank());
					rs.updateDouble("gem_xp_lvl", crew.getGemiddeldeXpLvl());
					rs.updateDouble("funding", crew.getFunding());
					rs.updateString("stichter_uuid", crew.getStichterUUID());
					rs.updateRow();
				}else{
					//bestaad niet
					rs.moveToInsertRow();
					rs.updateInt("id", crew.getId());
					rs.updateInt("rank", crew.getRank());
					rs.updateDouble("gem_xp_lvl", crew.getGemiddeldeXpLvl());
					rs.updateDouble("funding", crew.getFunding());
					rs.updateString("stichter_uuid", crew.getStichterUUID());
					rs.updateRow();
				}
				rs.close();
				if(string.equals("server")){
					SqlJDBC.sluitConnectie();
				}else{
					SqlLileJDBC.sluitConnectie();
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	private void leesDB(String string){
		try {
			if(string.equals("server")){
				ResultSet rs = SqlJDBC.voerQryUit("select * from crew;");
				rs.beforeFirst();
				while(rs.next()){
					int crewID = rs.getInt("id");
					Crew crew = new Crew(super.getMain(), crewID);
					crew.setRank(rs.getInt("rank"));
					crew.setGemiddeldeXpLvl(rs.getDouble("gem_xp_lvl"));
					crew.setFunding(rs.getDouble("funding"));
					crew.setStichterUUID(rs.getString("stchter_uuid"));
					super.getMain().getBestaandeCrews().add(crew);
				}
				rs.close();
			}else{

				ResultSet rs = SqlLileJDBC.voerQryUit("select * from crew;");
				rs.beforeFirst();
				while(rs.next()){
					int crewID = rs.getInt("id");
					Crew crew = new Crew(super.getMain(), crewID);
					crew.setRank(rs.getInt("rank"));
					crew.setGemiddeldeXpLvl(rs.getDouble("gem_xp_lvl"));
					crew.setFunding(rs.getDouble("funding"));
					crew.setStichterUUID(rs.getString("stchter_uuid"));
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

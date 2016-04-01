package mainserverjt.piratemod.db;

import java.io.PushbackReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.crew.Crew;
import mainserverjt.piratemod.crew.Pirate;
import net.minecraft.entity.player.EntityPlayer;

public class PirateHandler extends FileHandler{

	private String fileName;
	
	public PirateHandler(Main main){
		super(main);
	}
	
	/**
	 * gaat alle data laden die er is van deze player
	 * en voegd de player toe aan de online players
	 * en zorcht er voor dat de crew word gelinkt
	 * @param player die ingelogd is via evnt
	 */
	public void  loadData(EntityPlayer player){
		ResultSet rs = null;
		try{
			if(super.isGebruiktDB()){
				//server
				SqlJDBC.maakConnectie();
				rs = SqlJDBC.voerQryUit("select * from pirate where uuid = " + player.getUniqueID().toString());
				SqlJDBC.sluitConnectie();
			}else{
				//file
				SqlLileJDBC.maakConnectie();
				rs = SqlLileJDBC.voerQryUit("select * from pirate where uuid = " + player.getUniqueID().toString());
				SqlLileJDBC.sluitConnectie();
			}
			if(rs.last()){
				//bestaad
				rs.beforeFirst();
				rs.next();
				Pirate piraat = new Pirate(getMain(), player);
				piraat.setFunding(rs.getDouble("funding"));
				piraat.setLevel(rs.getFloat("lvl"));
				piraat.setRank(rs.getInt("rank"));
				piraat.setType(rs.getString("type"));
				piraat.setXpLvl(rs.getFloat("xp"));
				getMain().getOnlinePirates().add(piraat);
				for(Crew crew : getMain().getBestaandeCrews()){
					if(crew.getId() == rs.getInt("crew_id")){
						piraat.setCrew(crew);
					}
				}
			}else{
				//bestaad niet
				Pirate piraat = new Pirate(getMain(), player);
				getMain().getOnlinePirates().add(piraat);
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * gaat de speler gegevens opslaan
	 * de funktie verwacht de Pirate die ne t uit gelogd is
	 * @param p al Piraat
	 */
	public void saveData(Pirate p){
		try{
			boolean gevonden = false;
			ResultSet rs = null;
			if(isGebruiktDB()){
				//server
				SqlJDBC.maakConnectie();
				rs = SqlJDBC.voerQryUit("select * from pirate where uuid = " + p.getUniekID().toString());
			}else{
				//file
				SqlLileJDBC.maakConnectie();
				rs = SqlLileJDBC.voerQryUit("select * from pirate where uuid = " + p.getUniekID().toString());
			}
			if(rs.last()){
				//bestaat
				rs.moveToInsertRow();
				rs.updateInt("crew_id", p.getCrew().getId());
				rs.updateString("naam", p.getPlayer().getDisplayName());
				rs.updateInt("rank", p.getRank());
				rs.updateDouble("funding", p.getFunding());
				rs.updateString("type", p.getType());
				rs.updateFloat("lvl", p.getLevel());
				rs.updateFloat("xp", p.getXpLvl());
			}else{
				//bestaad niet
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}

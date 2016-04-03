package mainserverjt.piratemod.db;

import java.io.PushbackReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.crew.Crew;
import mainserverjt.piratemod.crew.Pirate;
import net.minecraft.entity.player.EntityPlayer;

public class PirateHandler extends FileHandler {

	private String fileName;

	public PirateHandler(Main main) {
		super(main);
	}

	/**
	 * gaat alle data laden die er is van deze player en voegd de player toe aan
	 * de online players en zorcht er voor dat de crew word gelinkt
	 * 
	 * @param player
	 *            die ingelogd is via evnt
	 * @return return de zo juist gemakte pirate
	 */
	public Pirate loadData(EntityPlayer player) {
		ResultSet rs = null;
		try {
			if (super.isGebruiktDB()) {
				// server
				SqlJDBC.maakConnectie();
				rs = SqlJDBC.voerQryUit("select * from pirate where uuid = " + player.getUniqueID().toString());
				SqlJDBC.sluitConnectie();
			} else {
				// file
				SqlLileJDBC.maakConnectie();
				rs = SqlLileJDBC.voerQryUit("select * from pirate where uuid = " + player.getUniqueID().toString());
				SqlLileJDBC.sluitConnectie();
			}
			if (rs != null && rs.last()) {
				// bestaad
				rs.beforeFirst();
				rs.next();
				Pirate piraat = new Pirate(getMain(), player);
				piraat.setFunding(rs.getDouble("funding"));
				piraat.setLevel(rs.getFloat("lvl"));
				piraat.setRank(rs.getInt("rank"));
				piraat.setType(rs.getString("type"));
				piraat.setXpLvl(rs.getFloat("xp"));
				getMain().getOnlinePirates().add(piraat);
				for (Crew crew : getMain().getBestaandeCrews()) {
					if (crew.getId() == rs.getInt("crew_id")) {
						piraat.setCrew(crew);
					}
				}
				return piraat;
			} else {
				// bestaad niet
				Pirate piraat = new Pirate(getMain(), player);
				getMain().getOnlinePirates().add(piraat);
				return piraat;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * gaat de speler gegevens opslaan de funktie verwacht de Pirate die net uit
	 * gelogd is
	 * 
	 * @param p
	 *            al Piraat
	 */
	public void saveData(Pirate p) {
		try {
			ResultSet rs = null;
			if (isGebruiktDB()) {
				// server
				SqlJDBC.maakConnectie();
				rs = SqlJDBC.voerQryUit("select * from pirate where uuid = " + p.getUniekID().toString());
			} else {
				// file
				SqlLileJDBC.maakConnectie();
				rs = SqlLileJDBC.voerQryUit("select * from pirate where uuid = " + p.getUniekID().toString());
			}
			if (rs != null && rs.last()) {
				// bestaat
				rs.beforeFirst();
				rs.next();
				rs.updateInt("crew_id", p.getCrew().getId());
				rs.updateString("naam", p.getPlayer().getDisplayName());
				rs.updateInt("rank", p.getRank());
				rs.updateDouble("funding", p.getFunding());
				rs.updateString("type", p.getType());
				rs.updateFloat("lvl", p.getLevel());
				rs.updateFloat("xp", p.getXpLvl());
				rs.updateRow();
			} else {
				// bestaad niet
				System.out.println("de piraat bestaat niet");
				String sql = "insert into pirate (uuid, crew_id, naam, rank, funding, type, lvl, xp) values ('"
						+ p.getUniekID().toString() + "','"
						+ p.getCrew().getId() + "','" 
						+ p.getPlayer().getDisplayName() + "','"
						+ p.getRank() + "','" 
						+ p.getFunding() + "','" 
						+ p.getType() + "','" 
						+ p.getLevel() + "','"
						+ p.getXpLvl() + "');";
				System.out.println("gebruikt db= " + isGebruiktDB());
				if (isGebruiktDB()) {
					SqlJDBC.sluitConnectie();
					// server
					SqlJDBC.maakConnectie();
					int i = SqlJDBC.voerUpdateUit(sql);
					System.out.println("geupdate index= " + i);
					SqlJDBC.sluitConnectie();
				} else {
					SqlLileJDBC.sluitConnectie();
					// file
					SqlLileJDBC.maakConnectie();
					SqlLileJDBC.voerQryUit(sql);
					SqlLileJDBC.sluitConnectie();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

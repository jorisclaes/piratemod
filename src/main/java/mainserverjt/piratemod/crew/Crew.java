package mainserverjt.piratemod.crew;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import mainserverjt.piratemod.Main;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class Crew {

	private ArrayList<Pirate> leden;
	private String stichterUUID;
	private String naamCrew;
	private final int id;
	private Main main;
	private int rank;
	private double gemiddeldeXpLvl;
	private double funding;
	
	
	
	//private Ship currentship;
	//private Weapon currentWeaponRank;
	/**
	 * maakt een crew aan
	 * @param main main variable 
	 * @param crewid id van crew
	 * @param leden de lijst van personen behoordende tot de crew
	 */
	public Crew(Main main,int crewid){
		this.main = main;
		id = crewid;
		rank = 0;
		gemiddeldeXpLvl = 0;
		funding = 0;
		
	}
	
	//setter
	/**
	 * set de rank
	 * @param rank int rank
	 */
	public void setRank(int rank){
		this.rank = rank;
	}
	
	
	/**
	 * returnt een rank
	 * @return rank as int
	 */
	//getter
	public int getRank(){
		return rank;
	}
	
	/**
	 * returnt een gemiddelde xp lvl
	 * @return double van gxl
	 */
	public double getGemiddeldeXpLvl() {
		return gemiddeldeXpLvl;
	}

	/**
	 * zet de variable als gxl
	 * @param gemiddeldeXpLvl
	 */
	public void setGemiddeldeXpLvl(double gemiddeldeXpLvl) {
		this.gemiddeldeXpLvl = gemiddeldeXpLvl;
	}

	/**
	 * return funding word gebruikt voor upgreads te kopen (weapon en ship)
	 * @return funding las intger
	 */
	public double getFunding() {
		return funding;
	}

	/**
	 * set de funding
	 * @param d int
	 */
	public void setFunding(double d) {
		this.funding = d;
	}

	/**
	 * returnt de piraten in deze Crew
	 * @return een groep van Pirate
	 */
	public ArrayList<Pirate> getPersonen() {
		return leden;
	}

	/**
	 * returnt id van de Crew
	 * @return als int
	 */
	public final int getId() {
		return id;
	}

	/**
	 * returnt de stichter van de crew
	 * 'De capitein'
	 * @return Piraat
	 */
	public String getStichterUUID() {
		return stichterUUID;
	}

	/**
	 * set de stichter van de crew
	 * @param UUID al Piraat
	 */
	public void setStichterUUID(String UUID) {
		this.stichterUUID = UUID;
	}
	
	/**
	 * saved alle data van de crew in de DB
	 */
	public void saveData(){
		throw new UnsupportedOperationException("crew save moet nog geschreven worden");
	}

	/**
	 * returnt de naam van de crew
	 * @return zls String
	 */
	public String getNaamCrew() {
		return naamCrew;
	}

	/**
	 * set de naam van de crew
	 * @param naamCrew als String
	 */
	public void setNaamCrew(String naamCrew) {
		this.naamCrew = naamCrew;
	}
	
	
}

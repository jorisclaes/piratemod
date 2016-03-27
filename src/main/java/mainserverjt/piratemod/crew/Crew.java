package mainserverjt.piratemod.crew;

import java.util.HashMap;

import mainserverjt.piratemod.Main;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class Crew {

	private Pirate[] personen;
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
	 * @param personen de lijst van personen behoordende tot de crew
	 */
	public Crew(Main main,int crewid, Pirate[] personen){
		this.main = main;
		id = crewid;
		this.personen = personen;
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
	 * @param funding int
	 */
	public void setFunding(int funding) {
		this.funding = funding;
	}

	/**
	 * returnt de piraten in deze Crew
	 * @return een groep van Pirate
	 */
	public Pirate[] getPersonen() {
		return personen;
	}

	/**
	 * returnt id van de Crew
	 * @return als int
	 */
	public final int getId() {
		return id;
	}
	
	
}

package mainserverjt.piratemod.crew;

import java.util.UUID;

import mainserverjt.piratemod.Main;

public class Pirate{
	
	private Main main;
	private Crew crew;
	private String naam;
	private int rank;
	private final UUID uniekID;
	private double funding;
	private String type; //wat die doet
	//private Gear gear;
	private float level;
	private int permissionLvl;
	
	/**
	 * maakt een nieuwe piraat aan
	 * @param main main variable
	 * @param naam de naam van di piraat
	 * @param unikId is de player zijn unik id
	 */
	public Pirate(Main main, String naam, UUID unikId){
		this.main = main;
		this.naam = naam;
		this.uniekID = unikId;
	}

	/**
	 * geeft de rank van de piraat
	 * @return rank als int
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * set de rank van de piraat
	 * @param rank als int
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * return hoe veel geld dat de persoon heeft 
	 * @return bedrag als double
	 */
	public double getFunding() {
		return funding;
	}

	/**
	 * set het bedrag dat deze persoon heeft
	 * @param funding bedrag als double
	 */
	public void setFunding(double funding) {
		this.funding = funding;
	}

	/**
	 * returnt het type van piraat deze persoon is
	 * @return type als String
	 */
	public String getType() {
		return type;
	}

	/**
	 * set het type van piraat dat dez persoon is
	 * @param type als String
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * returnt de piraat zijn huidig level
	 * @return als int 
	 */
	public float getLevel() {
		return level;
	}

	/**
	 * set het momentele level dat deze piraat is
	 * @param level als float
	 */
	public void setLevel(float level) {
		this.level = level;
	}

	/**
	 * return in welke crew deze piraat zit
	 * @return als Crew
	 */
	public final Crew getCrew() {
		return crew;
	}
	
	/**
	 * set de crew dat de piraat tot behoort
	 * @param c de toegewezen crew
	 */
	public void setCrew(Crew c){
		this.crew = c;
	}
	
	/**
	 * returnt wat het uniek id id van deze piraat is
	 * @return als UUID java.util
	 */
	public final UUID getUniekID() {
		return uniekID;
	}

	/**
	 * returnt de piraat zij permissie lvl
	 * @return 0 == kan niets
	 */
	public int getPermissionLvl() {
		return permissionLvl;
	}

	/**
	 * set de permissie lvls van de piraat
	 * @param permissionLvl int > 0
	 */
	public void setPermissionLvl(int permissionLvl) {
		this.permissionLvl = permissionLvl;
	}
	
	/**
	 * verwact een een variable van PermissionsHelper.(opWatJeWiltTesten)
	 * @param command benodige UseLvl
	 * @return 
	 * returnt rtue als de piraat een hoogenoe CommandLvl heeft:
	 * 		dus hij kan het command gebruiken
	 * 
	 * returnd false alhij niet voldoet aan het opgegeven lvl
	 */
	public boolean canPirateUseCommand(float command){
		if(this.permissionLvl < command){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * returnt de piraat zijn naam
	 * @return
	 */
	private final String getNaam(){
		return this.naam;
	}
}

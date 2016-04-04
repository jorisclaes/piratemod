package mainserverjt.piratemod.crew;

import java.util.UUID;

import javax.sound.sampled.UnsupportedAudioFileException;

import mainserverjt.piratemod.Main;
import net.minecraft.entity.player.EntityPlayer;

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
	private float xpLvl;
	private float permissionLvl;
	private EntityPlayer player;
	
	/**
	 * maakt een nieuwe piraat aan
	 * @param main main variable
	 * @param naam de naam van di piraat
	 * @param unikId is de player zijn unik id
	 */
	public Pirate(Main main, EntityPlayer player){
		this.main = main;
		this.naam = player.getDisplayName();
		this.uniekID = player.getUniqueID();
		this.player = player;
		this.xpLvl = 1;
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
		if(crew == null){
			crew = new Crew(main, -1);
		}
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
	public float getPermissionLvl() {
		return permissionLvl;
	}

	/**
	 * set de permissie lvls van de piraat
	 * @param permissionLvl int > 0
	 */
	public void setPermissionLvl(float permissionLvl) {
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
	
	/**
	 * returnt de player de bij deze piraat hoort
	 * @return EntityPlayer
	 */
	public EntityPlayer getPlayer(){
		return player;
	}

	/**
	 * returnt het huidig xp LVL van de piraat
	 * @return xpLvl float
	 */
	public float getXpLvl() {
		return (int) (xpLvl/100 +0.5)*100;
	}

	/**
	 * set het huidig xp LVL van de piraat
	 * @param xpLvl als foat
	 */
	public void setXpLvl(float xpLvl) {
		this.xpLvl = xpLvl;
		float x= 100;
		int y = 2;
		for(int i = 0; i < 100; i++){
			float f =(float) ((y/x)*((y+x)*y*55.55555)) ;
			x = f;
			y += 1;
			if((i+1) == level){
				if(this.xpLvl >= f){
					setLevel(getLevel()+1F);
					setXpLvl(getLevel()-f);
				}
			}
		}
	}
	
	
}

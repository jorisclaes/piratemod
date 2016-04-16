package mainserverjt.piratemod.db;

import java.awt.FontFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.exeptions.FileFormatExeption;

public class PermmisionHandler extends FileHandler{

	private String fileName = "PirateMod.Permissions";
	private HashMap<String, String[]>groepen;
	private HashMap<String, String[]>permissions;
	private ArrayList<String> groepNamen;
	private boolean permissionsIqnore;
	
	public PermmisionHandler(Main main){
		super(main);
		groepen = new HashMap<String, String[]>();
		permissions = new HashMap<String, String[]>();
		groepNamen = new ArrayList<String>();
		readFile();
	}
	
	/**
	 * gaat de file uitlezen
	 */
	public void readFile() {
		if(super.isFileExisting(fileName)){
			//file bestaat
			try {
				read();
			} catch (FileFormatExeption e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//file bestaat niet
			createFile();
		}
	}
	
	/**
	 * gaat de file aan maken met init text
	 */
	private void createFile(){
		String content = 
				"UserName:\n"+
						"\tPermissions:\n"+
							"\t\t- permission1\n"+
							"\t\t- permission2\n"+
				"Group:\n"+
						"\tName: Name\n"+
						"\tUsers:\n"+
							"\t\t- user1\n"+
							"\t\t- user2\n"+
						"\tPermissions:\n"+
							"\t\t- permission1\n"+
							"\t\t- permission2";
		super.saveFile(fileName, content);
	}
	
	/**
	 * gaat de file uitlezen
	 * @throws FileFormatExeption 
	 */
	private void read() throws FileFormatExeption{
		File file = new File(getPath()+fileName);
		try {
			Scanner scan = new Scanner(file);
			String hulp = "";
			if(!scan.hasNext()){
				System.out.println("File EMPTY permissions ignored");
				permissionsIqnore = true;
				scan.close();
				return;
			}else{
				permissionsIqnore = false;
			}
			hulp = scan.nextLine();
			if(hulp.contains("Group")){
				//permission file begint met de groeppen eerst
				hulp = leesGroeppen(scan, hulp);
				if(hulp == null) throwFileError(scan, "Goups Format Exeption");
				hulp = leesUsers(scan, hulp);
			}else{
				//permission file begint met de users eerst
				hulp = leesUsers(scan, hulp);
				if(hulp == null) throwFileError(scan, "Userest Format Exeption");
				leesGroeppen(scan, hulp);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//throw new UnsupportedOperationException();
	}
	
	/**
	 * returnt de personen in deze groep
	 * @param groepNaam naam van de groep
	 * @return String[] de personen in de groep
 	 */
	public String[] getUsersInGroup(String groepNaam){
		return groepen.get(groepNaam);
	}
	
	/**
	 * returnt de permissions per Persoon,
	 * als de groep naam is megegeven dan returnt die de permissons van de groep
	 * @param Persoon de persoon waarvan je wilt opvragen
	 * @return String[] de permissions die die heeft
	 */
	public String[] getPermissions(String Persoon){
		return permissions.get(Persoon);
	}
	
	/**
	 * returnt alle groep namen die er zijn
	 * dit is nodig om de users op te vragen
	 * @return alle groeppen die er zijn las ArryList
	 */
	public ArrayList<String> getGroeppen(){
		return groepNamen;
	}
	
	/**
	 * returnt of de permissions ge negert worden :)	
	 * @return true = genegeert
	 */
	public boolean isPermissionsIqnore() {
		return permissionsIqnore;
	}

	/**
	 * trowt een error ivbm de permission file
	 * @param scan
	 * @param message
	 * @throws FileFormatExeption
	 */
	private void throwFileError(Scanner scan, String message) throws FileFormatExeption{
		scan.close();
		throw new FileFormatExeption("Permission File Format Error: " + message + "\n");
	}
	
	/**
	 * gaat de groeppen uit lezen van de file
	 * @param Scanner
	 * @throws FileFormatExeption 
	 */
	private String leesGroeppen(Scanner scan, String hulp) throws FileFormatExeption{
		String naam = "";
		String[] per = {};
		String[] user = {};
		int aant = 0;
		if(!scan.hasNext()) throwFileError(scan, "Group Incompete");
		hulp = scan.nextLine();
		while(aant != 3){
			if(hulp.contains("Name")){
				aant += 1;
				naam = hulp.substring(hulp.indexOf(" ")+1);
				if(naam.isEmpty())throwFileError(scan, "Goup > Name: Elegal Argument");
				hulp = scan.nextLine();
			}else if (hulp.contains("Users")){
				aant += 1;
				if(!scan.hasNext()) throwFileError(scan, "Group > Users: Incomplete");
				String[] usrH = {};
				while(true){
					if(scan.hasNext()){
						hulp = scan.nextLine();
					}else{
						break;
					}
					if(hulp.contains("-")){
						usrH = new String[user.length+1];
						for(int i = 0; i < user.length; i++){
							usrH[i] = user[i];
						}
						usrH[usrH.length-1] = hulp.substring(hulp.indexOf(" ")+1);
						if(usrH[usrH.length-1].isEmpty()) throwFileError(scan, "Group > Permissions: Empty Permission After " + usrH[usrH.length-2]);
						user = usrH;
					}else{
						break;
					}
				}
			}else if (hulp.contains("Permissions")){
				aant += 1;
				if(!scan.hasNext()) throwFileError(scan, "Group > Permissions: Incompete");
				String[] perH = {};
				while(true){
					if(scan.hasNext()){
						hulp = scan.nextLine();
					}else{
						break;
					}
					perH = new String[per.length+1];
					if(hulp.contains("-")){
						for(int i = 0; i < per.length; i++){
							perH[i] = per[i];
						}
						perH[perH.length-1] = hulp.substring(hulp.indexOf(" ")+1);
						if(perH.length > 1){
							if(perH[perH.length-1].isEmpty()) throwFileError(scan, "Group > Permissions: Empty Permission After " + perH[perH.length-2]);
						}else{
							if(perH[perH.length-1].isEmpty()) throwFileError(scan, "Group > Permissions: Empty Permission After " + perH[perH.length-1]);
						}
						per = perH;
					}else{
						break;
					}
				}
			}else{
				throwFileError(scan, "Unknown Field: " + hulp);
			}
		}
		this.groepen.put(naam, user);
		this.permissions.put(naam, per);
		this.groepNamen.add(naam);
		if (scan.hasNext() && hulp.contains("Group")){
			leesGroeppen(scan, hulp);
		}
		return hulp;
	}
	
	/**
	 * gaat de gebruirs uit lezen van de file
	 * @param Scanner
	 * @throws FileFormatExeption 
	 */
	private String leesUsers(Scanner scan, String hulp) throws FileFormatExeption{
		if(!scan.hasNext()) return null;
		String naam = "";
		String[] per = {};
		if(hulp.contains(":")){
			naam = hulp.substring(0, hulp.indexOf(":"));
		}else{
			naam = hulp;
		}
		System.out.println("USERNAME= " + naam);
		if(!scan.hasNext()) throwFileError(scan, naam + ": Missing Permissions");
		hulp = scan.nextLine();
		if(hulp.contains("Permissions")){
			if(!scan.hasNext()) throwFileError(scan, naam + " > Permissions: Incomplete");
			String perH[] = {};
			while(true){
				if(scan.hasNext()){
					hulp = scan.nextLine();
					perH = new String[per.length+1];
					if(hulp.contains("-")){
						for(int i = 0; i < per.length; i++){
							perH[i] = per[i];
						}
						perH[perH.length-1] = hulp.substring(hulp.lastIndexOf(" ")+1);
						if(perH.length > 1){
							if(perH[perH.length-1].isEmpty()) throwFileError(scan, "Group > Permissions: Empty Permission After " + perH[perH.length-2]);
						}else{
							if(perH[perH.length-1].isEmpty()) throwFileError(scan, "Group > Permissions: Empty Permission After " + perH[perH.length-1]);
						}
						per = perH;
					}else{
						break;
					}
				}else{
					break;
				}
			}
			this.permissions.put(naam, per);
			if(scan.hasNext() && !hulp.contains("Group")){
				leesUsers(scan, hulp);
			}else{
				return hulp;
			}
		}else{
			throwFileError(scan, naam + ": Unknown Field: " + hulp);
		}
		return null;
	}
}

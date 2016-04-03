package mainserverjt.piratemod.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import mainserverjt.piratemod.Main;

public class PermmisionHandler extends FileHandler{

	private String fileName = "PirateMod.Permissions";
	private HashMap<String, String[]>groepen;
	private HashMap<String, String[]>permissions;
	private ArrayList<String> groepNamen;
	
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
			read();
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
						"\tName: 'Name'\n"+
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
	 */
	private void read(){
		File file = new File(getPath()+fileName);
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNext()){
				String hulp = scan.nextLine();
				String userName = hulp;
				String args[] = {};
				String argsH[];
				String groepName = "";
				String[] groepUsers = {};
				String[] permissionGroep = {};
				if(scan.hasNext()){
					hulp = scan.nextLine();
					if(hulp.contains("Permissions:")){
						while(true){
							hulp = scan.nextLine();
							if(hulp.contains("- ")){
								argsH = new String[args.length+1];
								for(int i = 0; i < args.length; i++){
									argsH[i] = args[i];
								}
								argsH[argsH.length-1] = hulp.substring(hulp.indexOf(" ")+1);
								args = argsH;
							}else{
								break;
							}
						}
					}					
				}
				if(args.length > 0){
					permissions.put(userName, args);
				}
				if(scan.hasNext()){
					hulp = scan.nextLine();
					if(hulp.contains("Group")){
						hulp = scan.nextLine();
						if(hulp.contains("Name")){
							args = hulp.split(":");
							args[1] = args[1].substring(args[1].indexOf("'")+1);
							args[1] = args[1].substring(0, args[1].indexOf("'"));
							groepName = args[1];
							groepNamen.add(groepName);
						}
						if(hulp.contains("Users")){
							if(scan.hasNext()){
								String[] userH;
								while(true){
									userH = new String[groepUsers.length+1];
									hulp = scan.nextLine();
									if(hulp.contains("-")){
										for(int i = 0; i < groepUsers.length; i++){
											userH[i] = groepUsers[i];
										}
										userH[userH.length-1] = hulp.substring(hulp.indexOf(" ")+1);
										groepUsers = userH;
									}else{
										break;
									}
								}
							}
						}
						if(hulp.contains("Permissions")){
							if(scan.hasNext()){
								String[] perH;
								while(true){
									perH = new String[permissionGroep.length+1];
									hulp = scan.nextLine();
									if(hulp.contains("-")){
										for(int i = 0; i < permissionGroep.length; i++){
											perH[i] = permissionGroep[i];
										}
										perH[perH.length-1] = hulp.substring(hulp.indexOf(" ")+1);
										permissionGroep = perH;
									}else{
										break;
									}
								}
							}
						}
						if(groepUsers.length > 0){
							groepen.put(groepName, groepUsers);
						}
						if(permissionGroep.length > 0){
							permissions.put(groepName, permissionGroep);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//throw new UnsupportedOperationException();
	}
	
	/**
	 * returnt de personen in deze groep
	 * @param groepNaam
	 * @return String[]
 	 */
	public String[] getUsersInGroup(String groepNaam){
		return groepen.get(groepNaam);
	}
	
	/**
	 * returnt de permissions per Persoon
	 * als de groep naam is megegeven da returnt die de permiisons per groep naam
	 * @param Persoon
	 * @return String[]
	 */
	public String[] getPermissions(String Persoon){
		return permissions.get(Persoon);
	}
	
	/**
	 * returnt alle groep namen die er zijn
	 * dit is nodig om de users op te vragen
	 * @return
	 */
	public ArrayList<String> getGroeppen(){
		return groepNamen;
	}
}

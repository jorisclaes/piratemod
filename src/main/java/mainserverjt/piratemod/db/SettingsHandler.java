package mainserverjt.piratemod.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.sun.java_cup.internal.runtime.Symbol;

import mainserverjt.piratemod.Main;

public class SettingsHandler extends FileHandler{

	private String fileName = "PirateMod.settings";
	
	public SettingsHandler(Main main) {
		super(main);
		// TODO Auto-generated constructor stub
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
				"GebruikDB: 'false'\n"+
				"\tURL: ''\n"+
				"\tuserName: ''\n"+
				"\tpass: ''";
		super.saveFile(fileName, content);
	}
	
	/**
	 * gaat de file uitlezen
	 */
	private void read(){
		try {
			Scanner scan = new Scanner(new File(getPath()+fileName));
			while(scan.hasNext()){
				String scaned = scan.nextLine();
				if(scaned.contains("GebruikDB")){
					String[] args = scaned.split(":");
					if(args[1].contains("true")){
						super.setGebruiktDB(true);
					}else{
						super.setGebruiktDB(false);
					}
				}
				if(scaned.contains("URL")){
					String[] args = scaned.split(":");
					args[1] = args[1].substring(args[1].indexOf("'")+1);
					args[1] = args[1].substring(0, args[1].indexOf("'"));
					SqlJDBC.url = args[1];
				}
				if(scaned.contains("userName")){
					String[] args = scaned.split(":");
					args[1] = args[1].substring(args[1].indexOf("'")+1);
					args[1] = args[1].substring(0, args[1].indexOf("'"));
					SqlJDBC.username = args[1];
				}
				if(scaned.contains("pass")){
					String[] args = scaned.split(":");
					args[1] = args[1].substring(args[1].indexOf("'")+1);
					args[1] = args[1].substring(0, args[1].indexOf("'"));
					SqlJDBC.passwoord = args[1];
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

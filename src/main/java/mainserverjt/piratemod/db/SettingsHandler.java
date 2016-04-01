package mainserverjt.piratemod.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

import com.sun.java_cup.internal.runtime.Symbol;

import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.crew.Crew;
import mainserverjt.piratemod.db.tabelInit.CrewTabel;
import mainserverjt.piratemod.db.tabelInit.PirateTable;

public class SettingsHandler extends FileHandler{

	private static String fileName = "PirateMod.settings";
	private static boolean dbBestaad = false;
	
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
		super.setGebruiktDB(false);
		SqlLileJDBC.maakConnectie();
		SqlLileJDBC.voerUpdateUit(CrewTabel.CREATE_SQL);
		SqlLileJDBC.voerUpdateUit(PirateTable.CREATE_SQL);
		SqlLileJDBC.voerUpdateUit(CrewTabel.ALTER_TABLE_SETINGS);
		SqlLileJDBC.voerUpdateUit(PirateTable.ALTER_TABLE_SETTINGS);
		SqlLileJDBC.voerUpdateUit(PirateTable.ALTER_TABLE_RESTRICT);
		SqlLileJDBC.sluitConnectie();
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
			try{
				SqlJDBC.maakConnectie();
				SqlJDBC.voerQryUit("select * from crew;");
				SqlJDBC.sluitConnectie();
				dbBestaad = true;
			}catch(SQLException ex){}
			
			if(super.isGebruiktDB() && !dbBestaad){
				try{
					SqlJDBC.maakConnectie();
					SqlJDBC.voerUpdateUit(CrewTabel.CREATE_SQL);
					SqlJDBC.voerUpdateUit(PirateTable.CREATE_SQL);
					SqlJDBC.voerUpdateUit(CrewTabel.ALTER_TABLE_SETINGS);
					SqlJDBC.voerUpdateUit(PirateTable.ALTER_TABLE_SETTINGS);
					SqlJDBC.voerUpdateUit(PirateTable.ALTER_TABLE_RESTRICT);
					SqlJDBC.sluitConnectie();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

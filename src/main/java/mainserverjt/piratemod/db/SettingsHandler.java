package mainserverjt.piratemod.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.command.ChatColor;
import mainserverjt.piratemod.crew.Crew;
import mainserverjt.piratemod.db.tabelInit.CrewTabel;
import mainserverjt.piratemod.db.tabelInit.PirateTable;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Chat;

public class SettingsHandler extends FileHandler{

	private static String fileName = "PirateMod.settings";
	
	public SettingsHandler(Main main) {
		super(main);
		// TODO Auto-generated constructor stub
		readFile();
	}

	/**
	 * gaat de file uitlezen
	 */
	public void readFile() {
		if(super.isFileExisting(fileName)){
			//file bestaat
			System.out.println("read file");
			read();
		}else{
			//file bestaat niet
			System.out.println("create file");
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
						"\tpass: ''\n"+
				"";
		super.saveFile(fileName, content);
		super.setGebruiktDB(false);
		SqlLileJDBC.maakConnectie();
		SqlLileJDBC.sluitConnectie();
		SqlLileJDBC.maakConnectie();
		SqlLileJDBC.voerUpdateUit(CrewTabel.CREATE_SQL);
		SqlLileJDBC.voerUpdateUit(CrewTabel.INSERT_DUMMY_CERW);
		SqlLileJDBC.voerUpdateUit(PirateTable.CREATE_SQL);
		SqlLileJDBC.voerUpdateUit(CrewTabel.ALTER_TABLE_SETINGS);
		SqlLileJDBC.voerUpdateUit(PirateTable.ALTER_TABLE_SETTINGS);
		SqlLileJDBC.voerUpdateUit(PirateTable.ALTER_ADD_PIAMRY_KEY);
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
					System.out.println("URL = " + args[1]);
					SqlJDBC.url = args[1];
				}
				if(scaned.contains("userName")){
					String[] args = scaned.split(":");
					args[1] = args[1].substring(args[1].indexOf("'")+1);
					args[1] = args[1].substring(0, args[1].indexOf("'"));
					System.out.println("username = " + args[1]);
					SqlJDBC.username = args[1];
				}
				if(scaned.contains("pass")){
					String[] args = scaned.split(":");
					args[1] = args[1].substring(args[1].indexOf("'")+1);
					args[1] = args[1].substring(0, args[1].indexOf("'"));
					SqlJDBC.passwoord = args[1];
					System.out.println("passwoord = " + args[1]);
				}
			}
			scan.close();
			System.out.println("naar TRY");
			try{
				if(!isGebruiktDB())return;
				System.out.println("maak con");
				SqlJDBC.maakConnectie();
				System.out.println("con ok");
				System.out.println("vraag rs");
				ResultSet rs = SqlJDBC.voerQryUit("select * from crew;");
				System.out.println("return rs");
				SqlJDBC.sluitConnectie();
				if(rs == null){
					try{
						SqlJDBC.maakConnectie();
						System.out.println("start init DB");
						SqlJDBC.voerUpdateUit(CrewTabel.CREATE_SQL);
						SqlJDBC.voerUpdateUit(CrewTabel.INSERT_DUMMY_CERW);
						SqlJDBC.voerUpdateUit(PirateTable.CREATE_SQL);
						SqlJDBC.voerUpdateUit(CrewTabel.ALTER_TABLE_SETINGS);
						SqlJDBC.voerUpdateUit(PirateTable.ALTER_TABLE_SETTINGS);
						SqlJDBC.voerUpdateUit(PirateTable.ALTER_ADD_PIAMRY_KEY);
						SqlJDBC.sluitConnectie();
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
			}catch(SQLException ex){}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

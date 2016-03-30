package mainserverjt.piratemod.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import mainserverjt.piratemod.Main;

public class PermmisionHandler extends FileHandler{

	private String fileName;
	private HashMap<String, String[]>groepen;
	
	public PermmisionHandler(Main main){
		super(main);
		groepen = new HashMap<String, String[]>();
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
						"\tPermissions:\n"+
							"\t\t- permission1\n"+
							"\t\t- permission2";
		super.saveFile(fileName, content);
	}
	
	/**
	 * gaat de file uitlezen
	 */
	private void read(){
		throw new UnsupportedOperationException();
	}
}

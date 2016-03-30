package mainserverjt.piratemod.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import mainserverjt.piratemod.Main;

public class FileHandler {
	
	private Main main;
	private boolean gebruiktDB;
	private static String path;
	
	public FileHandler(Main main){
		this.main = main;
		path = "config/PirateMod/";
	}

	/**
	 * returnt of er een DB wordt gebruikt
	 * @return true als er een DB gebuikt wordt
	 */
	private boolean isGebruiktDB() {
		return gebruiktDB;
	}

	/**
	 * gaat kijken of de file er is
	 * zoja returnt die true
	 * @param fileName als String
	 * @return flag als boolean
	 */
	private boolean isFileExisting(String fileName){
		File file = new File(fileName + fileName);
		if(file.exists() && !file.isDirectory()){
			return true;
		}
		return false;
	}
	
	/**
	 * gaat de file maken en wegschrijven
	 * @param fileName de naam van de file waar het opgelagen moet worden
	 * @param content de inhoud van de file
	 * @return de gesavede file
	 * 		moest het mislukken dan returnt die null
	 */
	private File saveFile(String fileName, String content){
		try{
			File f = new File(path + fileName);
			File dir = new File(path);
			if(!dir.exists()){
				dir.mkdirs();
			}
			Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
			w.write(content);
			w.close();
			return f;
		}catch(IOException ex){
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * returnt het pad waar de file moet opgelagen worden
	 * @return path las String
	 */
	private static String getPath() {
		return path;
	}
	
	
}

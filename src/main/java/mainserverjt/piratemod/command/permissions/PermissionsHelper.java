package mainserverjt.piratemod.command.permissions;

import java.util.HashMap;

public class PermissionsHelper {
	
	public static final float f_helpCommand = 0.0001F;
	public static final String s_helpCommand = "MainserverJT.PirateMod.helpCommand";
	
	public static final float f_registerCommand = 0.0002F;
	public static final String s_registerCommand = "MainserverJT.PirateMod.registerCommand";
	
	public static final float f_registerCommandCrew = 0.00021F;
	public static final String s_registerCommandCrew = "MainserverJT.PirateMod.registerCommand.crew";
	
	public static final float f_registerCommandTurnooi = 0.00022F;
	public static final String s_registerCommandTurnooi = "MainserverJT.PirateMod.registerCommand.turnooi";
	
	public static final float f_SupperUse = 999999999.999999999F;
	public static final String s_SupperUse = "MainserverJT.PirateMod.*";
	
	public static final HashMap<String, Float> permissions = new HashMap<String, Float>();
	
	public static void initPermisions(){
		permissions.put(s_helpCommand, f_helpCommand);
		permissions.put(s_registerCommand, f_registerCommand);
		permissions.put(s_registerCommandCrew, f_registerCommandCrew);
		permissions.put(s_registerCommandTurnooi, f_registerCommandTurnooi);
		permissions.put(s_SupperUse, f_SupperUse);
	}
}

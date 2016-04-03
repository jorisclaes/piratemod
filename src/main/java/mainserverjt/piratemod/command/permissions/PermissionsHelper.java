package mainserverjt.piratemod.command.permissions;

import java.util.HashMap;

public class PermissionsHelper {
	
	public static final float f_helpCommand = 0.0001F;
	public static final String s_helpCommand = "MainserverJT.PirateMod.helpCommand";
	
	public static final float f_SupperUse = 999999999.999999999F;
	public static final String s_SupperUse = "MainserverJT.PirateMod.*";
	
	public static final HashMap<String, Float> permissions = new HashMap<String, Float>();
	
	public static void initPermisions(){
		permissions.put(s_helpCommand, f_helpCommand);
		permissions.put(s_SupperUse, f_SupperUse);
	}
}

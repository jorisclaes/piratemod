package mainserverjt.piratemod.command;

import com.sun.security.auth.callback.TextCallbackHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import mainserverjt.piratemod.Main;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class ChatColor {
	
	public static final String wit = ";0$";
	private static final int id_wit = 0;

	public static final String rood = ";1$";
	private static final int id_rood = 1;
	
	public static final String groen = ";2$";
	private static final int id_groen = 2;

	public static final String goud = ";3$";
	private static final int id_goud = 3;

	public static final String aqwa = ";4$";
	private static final int id_aqwa = 4;

	public static final String blauw = ";5$";
	private static final int id_blauw = 5;

	public static final String geel = ";6$";
	private static final int id_geel = 6;
	
	public static final String donker_blauw = ";7$";
	private static final int id_donker_blauw = 7;
	
	public static final String grijs = ";8$";
	private static final int id_grijs = 8;
	
	public static final String zwart = ";9$";
	private static final int id_zwart = 9;
	
	public static final String lichtPaars = ";10$";
	private static final int id_lichtPaars = 10;
	
	
	public static final String prefix = zwart + "[" + grijs + Main.modName + zwart + "]" + wit + " ";
	
	//public static final String fake = ";50$";
	/**
	 * zet de text om naar een gekleurde text
	 * @param message orgineele text
	 * @return geleure text
	 */
	private static ChatComponentText addColor(String message){
		String[] args = message.split(";");
		ChatComponentText textM = new ChatComponentText("");
		int id = 0;
		for (String s : args) {
			int check = s.indexOf("$");
            if (check != -1){
                 id = Integer.parseInt(s.substring(0, check));
                 s = s.substring(check+1);
            }
			ChatComponentText text = null;
            ChatStyle style = null;
            //p_71515_1_.addChatMessage(new ChatComponentText(""+id));
			switch (id) {
			case id_wit:
				text = new ChatComponentText(s);
				style = new ChatStyle();
	            style.setColor(EnumChatFormatting.WHITE);
	            text.setChatStyle(style); 
	            textM.appendSibling(text);
				break;
			case id_rood:
				text = new ChatComponentText(s);
				style = new ChatStyle();
				style.setColor(EnumChatFormatting.RED);
				text.setChatStyle(style);
				textM.appendSibling(text);
				break;
			case id_groen:
				text = new ChatComponentText(s);
				style = new ChatStyle();
				style.setColor(EnumChatFormatting.GREEN);
				text.setChatStyle(style);
				textM.appendSibling(text);
				break;
			case id_goud:
				text = new ChatComponentText(s);
				style = new ChatStyle();
				style.setColor(EnumChatFormatting.GOLD);
				text.setChatStyle(style);
				textM.appendSibling(text);
				break;
			case id_aqwa:
				text = new ChatComponentText(s);
				style = new ChatStyle();
				style.setColor(EnumChatFormatting.AQUA);
				text.setChatStyle(style);
				textM.appendSibling(text);
				break;
			case id_blauw:
				text = new ChatComponentText(s);
				style = new ChatStyle();
				style.setColor(EnumChatFormatting.BLUE);
				text.setChatStyle(style);
				textM.appendSibling(text);
				break;
			case id_geel:
				text = new ChatComponentText(s);
				style = new ChatStyle();
				style.setColor(EnumChatFormatting.YELLOW);
				text.setChatStyle(style);
				textM.appendSibling(text);
				break;
			case id_donker_blauw:
				text = new ChatComponentText(s);
				style = new ChatStyle();
				style.setColor(EnumChatFormatting.DARK_BLUE);
				text.setChatStyle(style);
				textM.appendSibling(text);
				break;
			case id_grijs:
				text = new ChatComponentText(s);
				style = new ChatStyle();
				style.setColor(EnumChatFormatting.GRAY);
				text.setChatStyle(style);
				textM.appendSibling(text);
				break;
			case id_zwart:
				text = new ChatComponentText(s);
				style = new ChatStyle();
				style.setColor(EnumChatFormatting.BLACK);
				text.setChatStyle(style);
				textM.appendSibling(text);
				break;
			case id_lichtPaars:
				text = new ChatComponentText(s);
				style = new ChatStyle();
				style.setColor(EnumChatFormatting.LIGHT_PURPLE);
				text.setChatStyle(style);
				textM.appendSibling(text);
				break;
			}
		}
		return textM;
	}
	
	/**
	 * stuurt een bericht naar één persoon eventueel met cleurtjes
	 * @param p_71515_1_ depersoon naar wie je gaat sturen
	 * @param message het beticht
	 */
	public static void sendPrivateMessage(ICommandSender p_71515_1_, String message){
		p_71515_1_.addChatMessage(addColor(message));
	}
	
	/**
	 * stuurt een bericht naar iedereen op de server
	 * @param message de boodschap
	 */
	public static void sendBroadcastMessage(String message){
		  FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(addColor(message));
	}
	
	/**
	 * stuurt een bericht naar meerderemensen privé
	 * @param p_71515_3_ de groep van persoonen
	 * @param message de bootschap
	 */
	public static void sendPrivateMessageToMultiple(ICommandSender[] p_71515_3_, String message){
		for(ICommandSender s : p_71515_3_){
			sendPrivateMessage(s, message);
		}
	}
}

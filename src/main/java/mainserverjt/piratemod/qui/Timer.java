package mainserverjt.piratemod.qui;

import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.command.ChatColor;

public class Timer extends Thread implements Runnable {
	
	public Main main;
	
	private int tijd;
	private boolean run;
	
	public Timer(Main main){
		this.main = main;
		tijd = 0;
		run = false;
	}
	
	/**
	 * zet de tijd dat de timer gaat wachten
	 * @param sec tijd in sec
	 */
	public void setTimer(int sec){
		tijd = sec;
	}
	
	/**
	 * returnt de tijd dat de timer nog wacht
	 * @return
	 */
	public int getTimer(){
		return tijd;
	}
	
	/**
	 * zet of tde timet mag lopen of niet
	 * @param flag true == start
	 * @return returnt gelukt of niet
	 */
	public boolean setRun(boolean flag){
		this.run = flag;
		if(flag && tijd > 0){
			start();
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void run() {
		try {
			while(run){
				this.sleep(1000);
				tijd -= 1;
				if(tijd % 2 == 0){
					ChatColor.sendBroadcastMessage(ChatColor.prefix + "Qui will close in " + tijd + " sec");
				}
				if(tijd == 0 || !run){
					run = false;
					main.qui.setQuiOpen(false);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package mainserverjt.piratemod.command;

import mainserverjt.piratemod.Main;

public class Timer extends Thread implements Runnable {
	
	public Main main;	
	
	public Timer(Main main) {
		this.main = main;
	}


	public void run() {
		int tijd = 30;//in sec
		try {
			while(tijd >= 0){
				sleep(1000);
				if(tijd == 0){
					main.commandMain.status.setQuiStatusOpen(false);
					return;
				}
				if(tijd <= 10)
					main.commandMain.sendBrodcastMesage("Qui closes in " + tijd + " sec");
				if(tijd == 20)
					main.commandMain.sendBrodcastMesage("Qui Closes in " + tijd + " sec");
				
				tijd--;
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}


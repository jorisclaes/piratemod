package test;

public class TestBerekiningXpGain {
	
	public static void main(String[] args){
		int lvlTegne = 5;
		int regenstadnerXp = 25124855;
		int eigenLvl = 1;
		double f = (regenstadnerXp/100.0)*(eigenLvl/100.0)*lvlTegne;
		System.out.println(f);
	}

}
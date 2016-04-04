package test;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double x= 100;
		int y = 2;
		for(int i = 0; i < 100; i++){
			float f =(float) ((y/x)*((y+x)*y*55.55555)) ;
			f =  (float) ((f * 100+ 0.5) / 100);
			System.out.println("lvl: " + (i+1) + " -> "+ f);
			x = f;
			y += 1;
		}

	}

}

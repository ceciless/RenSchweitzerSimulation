package aeroport.utils;


public class FonctionUtils {
	
	public static String avionName(int i){
		if (i>2599){
			return avionName(i%2600);
		}
		else{
			int t1 = i/100;
			int t2 = (i-100*t1)/10;
			int t3=(i-t2*10-t1*100);		
			String result = String.valueOf(Character.toChars(65+t1))+String.valueOf(t2)+String.valueOf(t3);
			return result;
		}
	}
	

	
}

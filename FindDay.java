package projectMain;

public class FindDay extends Root {
	
	public static String findDay(String input) {
		
		String days[] = {"Monday","Tuesday","Wednsday","Thursday","Friday","Weekend"};//scans and applies correct day from save file info
		String day = "";
		
		for (int counter = 0; counter<=5; counter++) {
			int position = input.lastIndexOf(days[counter]);
			if (position != -1) {
				day = days[counter];
			}
		}
		
		return day;
	}
}

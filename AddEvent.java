package projectMain;

public class AddEvent extends Root {
	
	public static String addEvent(String name, String time, String day, String subject, String note) {

		String compiled_info = subject+":  "+name+" is due at "+time+" on "+day+". ";
		return compiled_info;
	}
}

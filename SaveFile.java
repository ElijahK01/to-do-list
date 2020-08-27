package projectMain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SaveFile extends Root {
	public static void saveFile(@SuppressWarnings("rawtypes") List saveInfo) throws IOException{
		try {
			try {Files.deleteIfExists(Paths.get("C:/yourdirectory/filename.txt"));}catch(IOException e) {}//deletes old save file
			FileWriter writer = new FileWriter("C:/yourdirectory/filename.txt");//opens file
			saveInfo.toString();
			writer.write(saveInfo.toString());//writes info to file
			writer.close();
		} catch (IOException e) {
			System.out.println("Error occurred when saving file");
		}
	}
	public static String getFile() throws IOException{
		String st = null;
		String reportedInfo = null;
		boolean lineFlag = false;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:/yourdirectory/filename.txt")); 
			
			while ((st = br.readLine()) != null) {//gets text from save file
				 if (lineFlag == false) {
					 reportedInfo = st;
				 }
				 lineFlag = true;
			}
			br.close();
		}catch(IOException e) {}
		
		return reportedInfo;
	}
}

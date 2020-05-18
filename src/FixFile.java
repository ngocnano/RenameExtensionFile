
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FixFile {
	
	public String path;
	public ArrayList<String> listData;
	
	public FixFile(String path) {
		this.path = path;
		listData = new ArrayList<String>();
	}
	
	public void readFile() throws IOException {
		Scanner scanner = new Scanner(new File(path));
		path = path.replace("txt", "srt");
		
		File fileSRT = new File(path);
		fileSRT.createNewFile();
		
		while(scanner.hasNextLine()) {
			String temString = scanner.nextLine();
			listData.add(temString);
		}
		
		try {
			FileWriter fWriter = new FileWriter(fileSRT);
			
			for(String temp : listData) {
				
				if(temp.contains(":")) {
					temp = modefiedLine(temp);
				}
				
				fWriter.write(temp);
				fWriter.write("\n");
			}
			fWriter.close();
			scanner.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		System.out.println("Create " + path);
	}
	
	private String modefiedLine(String line) {
		
		StringBuilder temp = new StringBuilder(line);
		
		for(int i = 0; i < temp.length(); i++) {
			if(temp.charAt(i) == ':' && (i != (temp.length() - 1))) {
				
				temp.deleteCharAt(i+1);
			}
			if(temp.charAt(i) == '-') {
				temp.insert(i, "-");
				i++;
			}
			
			System.out.println(temp);
		}
		
		line = temp.toString();
		return line;
	}

}


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
		
		boolean check = false;
		while(scanner.hasNextLine()) {
			String temString = scanner.nextLine();
	
			listData.add(temString);
		}
		
		for(int i = 0; i < listData.size(); i++) {
			
			System.out.println("lis ta ta" + listData.size());
			
			if(listData.get(i).contains(":")) {

				if(listData.get(i+1).length() == 0) {
					
					listData.remove(i+1);
					System.out.println(listData.size());
				
				}
			
			}
			
		}
		
		
		try {
			FileWriter fWriter = new FileWriter(fileSRT);
			
			boolean checkEnter;
			
			for(int i = 0; i < listData.size();i++) {
				
				checkEnter = false;
				
				String temp = listData.get(i);
				
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
		
		if(line.contains(",")) line.replace(',', '.'); 
		
		return line;
	}

}

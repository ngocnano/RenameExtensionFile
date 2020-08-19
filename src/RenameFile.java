
import java.util.Scanner;

import java.io.*;

public class RenameFile {
	private String path;
	private File[] listFiles;
	private String oldEnd; //old file extension 
	private String newEnd; //new file extension
	
	public RenameFile(String oldEnd, String newEnd) {
		this.oldEnd = oldEnd;
		this.newEnd = newEnd;
	}
	
	public void loadListFile(String path) throws IOException {
		this.path = path;
		listFiles = new File(this.path).listFiles();
		
		for(File file : listFiles) {
			
			if(file.isFile()) {
				String temp = file.getPath();
				String exten = temp.substring(temp.length() - 4, temp.length());
				
				if(exten.equals(oldEnd)) {
					temp = temp.replace(oldEnd, newEnd);
					file.renameTo(new File(temp));
					System.out.println("rename " + temp);
				}
				
			 if(temp.contains("txt")) {
			     FixFile fixFile = new FixFile(temp);
			    fixFile.readFile();
			}

			}
			else if (file.isDirectory()) {
				loadListFile(file.getPath());
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RenameFile renameFile = new RenameFile(".srt", ".txt");

		Scanner scanner = new Scanner(System.in);
		


			
		System.out.print("Enter Path: ");
		String path = scanner.nextLine();

		
		//FixFile file = new FixFile(path);
		    try {
			//file.readFile();
			    renameFile.loadListFile(path);
		    } catch (IOException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
		    }
		
		
		scanner.close();
	
	}

}

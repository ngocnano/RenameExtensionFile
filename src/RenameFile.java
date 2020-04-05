import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class RenameFile {
	private String path;
	private File[] listFiles;
	private String oldEnd; //old extension file
	private String newEnd; //new extension file
	
	public RenameFile(String oldEnd, String newEnd) {
		this.oldEnd = oldEnd;
		this.newEnd = newEnd;
	}
	
	public void loadListFile(String path) {
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
			}
			else if (file.isDirectory()) {
				loadListFile(file.getPath());
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RenameFile renameFile = new RenameFile(".vtt", ".txt");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Path: ");
		String path = scanner.nextLine();
		renameFile.loadListFile(path);	
	}

}

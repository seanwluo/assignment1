package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler
{
	private String _fileName;
	
	public  FileHandler(String fileName)
	{
		this._fileName = fileName;
	}
	//check file exists
	
	/*
	 * Create method to read from file
	 */
	public List<String> read()
	{
		List<String> dataArray = new ArrayList<String>();
		
		try {
		
			BufferedReader input = new BufferedReader(new FileReader (_fileName));
		    String data = null;
		    
		    do {
				data = input.readLine();
				if (data != null)
				{
					dataArray.add(data);
				}
			} while (data != null);
			
			input.close();
		} catch (IOException e) {
			System.out.println("File Reading Error!!");
		}
		
		return dataArray;
	}
}

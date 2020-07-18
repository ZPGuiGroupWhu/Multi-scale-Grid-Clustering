package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {

	@SuppressWarnings("resource")
	public static List<String> GetData(String filepath) throws SQLException, ClassNotFoundException, IOException
	{
		File csv = new File(filepath);
		BufferedReader br = null;
	    try
	    {
	        br = new BufferedReader(new FileReader(csv));
	    } catch (FileNotFoundException e)
	    {
	        e.printStackTrace();
	    }
	    String line = "";
	    String everyLine = "";
	    List<String> allString = new ArrayList<>();
	    while ((line = br.readLine()) != null){
	        	everyLine = line;
	            allString.add(everyLine);
	    }
	    return allString;
	}
	/*public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		//List<String> rstream = ReadCSV.GetData();
	    //System.out.println(rstream.get(0).substring(0,rstream.get(0).lastIndexOf(",")));
	    //System.out.println(rstream.size());	    
	}*/
}


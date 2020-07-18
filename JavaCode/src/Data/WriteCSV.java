package Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class WriteCSV {
	public static void WriteData(String filePath,List<String> grid) throws SQLException, ClassNotFoundException, IOException{
		//String filePath = "C:\\Users\\Administrator\\Desktop\\2048_3.csv";
	    try{
	        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath),false));
	        for(int i=0;i<grid.size();i++){
	        	writer.write(grid.get(i)+"\r\n");
	        }
	        writer.close();
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	}
}

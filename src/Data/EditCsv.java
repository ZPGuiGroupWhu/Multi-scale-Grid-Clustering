package Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EditCsv {
	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
	List<String> rstream = ReadCSV.GetData("C:\\Users\\Administrator\\Desktop\\results_DBSCAN_taxi\\dbscan4.csv");
	String filePath = "C:\\Users\\Administrator\\Desktop\\results_DBSCAN_taxi\\\\dbscan8.csv";
	try{
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath),false));
		for (int i=0;i<rstream.size();i++) {
				int token1 = rstream.get(i).indexOf(",");
				int token2 = rstream.get(i).indexOf(",",token1+1);
				String Loc = rstream.get(i).substring(0,token2);
				String Id = rstream.get(i).substring(token2+1,rstream.get(i).length());
				if(Double.valueOf(Id.toString())!=0) {
					writer.write(Loc+","+Id+"\r\n");
				}
			}
		 writer.close();	
	 }catch(Exception e){
	        e.printStackTrace();
	    }
	}
}

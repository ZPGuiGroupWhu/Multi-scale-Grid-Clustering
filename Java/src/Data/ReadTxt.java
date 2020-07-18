package Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadTxt {
	public static String TransTxt2Json(String pathname) throws IOException{
		//String pathname = "C:\\Users\\geo1\\Desktop\\Grid_Json.txt"; 
        File filename = new File(pathname); 
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); 
        BufferedReader br = new BufferedReader(reader); 
        String line = "";  
        List<String> list = new ArrayList<String>();
        while ((line = br.readLine()) != null) {  
            list.add(line);
        }  
 
		StringBuffer sb = new StringBuffer();
	    boolean first = true;
	    sb.append("[");
		for(int i=0;i<list.size();i++){
			int token1 = list.get(i).indexOf(",");
			int token2 = list.get(i).indexOf(",",token1+1);
			int token3 = list.get(i).indexOf(",",token2+1);
			String index = list.get(i).substring(0,token1);
			String density = list.get(i).substring(token1+1,token2);
			String region = list.get(i).substring(token2+1,token3);
			String area = list.get(i).substring(token3+1,list.get(i).length());
		         if (!first) {
		             sb.append(",");
		         }
		         sb.append("{");
		         sb.append( (char)34+"index"+(char)34 +":"+(char)34+index+(char)34+",");
		         sb.append((char)34+"density"+(char)34 +":"+(char)34+density+(char)34+",");
		         sb.append((char)34+"region"+(char)34 +":"+(char)34+region+(char)34+",");
		         sb.append((char)34+"area"+(char)34 +":"+(char)34+area+(char)34);
		         sb.append("}");
		         first = false;
		}
		sb.append("]");
		
		String strJson = new String(sb); 
		return strJson;
	}
		/*public static void main(String[] args) throws IOException {
			int[] GridNum = new int[9];
			String[] grid = {"","","","","","","","",""};
			for(int i=0;i<9;i++){
				GridNum[i] = (int)(4096/(Math.pow(2,i)));
				String url = "C:\\Users\\geo1\\Desktop\\Grid_Json\\Grid_Json_"+""+GridNum[i]+""+".txt";
				//System.out.println(url);
				grid[i] = ReadTxt.TransTxt2Json(url); 
			}
			System.out.println(grid[5]);

}*/

}

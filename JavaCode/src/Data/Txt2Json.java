package Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class Txt2Json {
	public static String txt2json(String pathname) throws IOException{
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
			String Lng = list.get(i).substring(0,token1);
			String Lat = list.get(i).substring(token1+1,token2);
			String Id = list.get(i).substring(token2+1,list.get(i).length());
		         if (!first) {
		             sb.append(",");
		         }
		         sb.append("{");
		         sb.append( (char)34+"source"+(char)34 +":"+(char)34+Lng+(char)34+",");
		         sb.append((char)34+"target"+(char)34 +":"+(char)34+Lat+(char)34+",");
		         sb.append((char)34+"weight"+(char)34 +":"+(char)34+Id+(char)34);
		         sb.append("}");
		         first = false;
		}
		sb.append("]");
		
		String strJson = new String(sb); 
		return strJson;
	}
	public static void main(String[] args) throws IOException {
		String url = "C://Users//geo1//Desktop//个人出行预测数据//10.22//981808_static_loc_pairs_del_1.txt";
		String res = Txt2Json.txt2json(url);
		System.out.println(res);
	}
}

package Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetClassPolygon {
	public static String GetBoundIndex(int grid_num)throws IOException{
		String pathname = "C:\\Users\\geo1\\Desktop\\Grid_Json.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径  
        File filename = new File(pathname); // 要读取以上路径的input。txt文件  
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader  
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
        String line = "";  
        List<String> list = new ArrayList<String>();
        while ((line = br.readLine()) != null) {  
            int token_fst = line.indexOf(",");
			int token_lst = line.lastIndexOf(",");
			String index = line.substring(0,token_fst);
			String region = line.substring(token_lst+1,line.length());
            list.add(index+","+region);
        }  
        int num = list.size();
        int [][] grid = new int[num][3];
        int class_num = 1;
        for(int i=0;i<num;i++){
        	int position = Integer.valueOf(list.get(i).substring(0,list.get(i).indexOf(","))).intValue();
        	grid[i][0] = position%grid_num;
        	grid[i][1] = (int) Math.floor(position/grid_num);
        	grid[i][2] = Integer.valueOf(list.get(i).substring(list.get(i).indexOf(",")+1,list.get(i).length())).intValue();
        	if(grid[i][2]>class_num){
        		class_num = grid[i][2];
        	}
        }
        int[] class_count = new int[class_num];
        for(int i=0;i<class_num;i++){
        	class_count[i] = 0;
        	for(int j=0;j<num;j++){
        		if(grid[j][2]==(i+1)){
        			class_count[i]++;
        		}
        	}
        }
		Double grid_width = (135.1-73.5)/grid_num;
		Double grid_height = (53.6-18.1)/grid_num;
        List<Integer> top = new ArrayList<Integer>();
        List<Integer> bottom = new ArrayList<Integer>();
        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();
		StringBuffer sb = new StringBuffer();
	    boolean first = true;
	    sb.append("[");
        for(int i=1;i<class_num+1;i++){
        	if(class_count[i-1]>1){
	            int min_raw = Integer.MAX_VALUE;
	            int max_raw = 0;
	            int min_col = Integer.MAX_VALUE;
	            int max_col = 0;
	        	for(int j=0;j<num;j++){
	        		if(grid[j][2]==i){
	        			if(grid[j][0]>max_col){
	        				max_col = grid[j][0];
	        			}
	        			if(grid[j][0]<min_col){
	        				min_col = grid[j][0];
	        			}
	        			if(grid[j][1]>max_raw){
	        				max_raw = grid[j][1];
	        			}
	        			if(grid[j][1]<min_raw){
	        				min_raw = grid[j][1];
	        			}
	        		}
	        	}
	        	for(int k=min_col;k<max_col+1;k++){
					int min_r = grid_num;
					int max_r = 0;
	        		for(int j=0;j<num;j++){
	        			if(grid[j][2]==i&&grid[j][0]==k){
	        				if(grid[j][1]>max_r){
	        					max_r = grid[j][1];
	        				}
	        				if(grid[j][1]<min_r){
	        					min_r = grid[j][1];
	        				}
	        			}
	        		}
	        		top.add(max_r);
	        		bottom.add(min_r);
	        	}
	        	for(int k=bottom.get(max_col-min_col);k<top.get(max_col-min_col)+1;k++){
					int max_c = 0;
	        		for(int j=0;j<num;j++){
	        			if(grid[j][2]==i&&grid[j][1]==k){
	        				if(grid[j][0]>max_c){
	        					max_c = grid[j][0];
	        				}
	        			}
	        		}
	        		right.add(max_c);
	        	}
	        	for(int k=bottom.get(0);k<top.get(0)+1;k++){
					int min_c = grid_num;
	        		for(int j=0;j<num;j++){
	        			if(grid[j][2]==i&&grid[j][1]==k){
	        				if(grid[j][0]<min_c){
	        					min_c = grid[j][0];
	        				}
	        			}
	        		}
	        		left.add(min_c);
	        	}
		        if (!first) {
		            sb.append(",");
		        }
		        sb.append("{"+(char)34+"type"+(char)34+":" +(char)34+"Feature"+(char)34+","+(char)34+"properties"+(char)34+":{");
		        sb.append( (char)34+"classID"+(char)34 +":"+(char)34+i+(char)34+"},");
		        sb.append((char)34+"geometry"+(char)34 +":{"+(char)34+"type"+(char)34+":"+(char)34+"Polygon"+(char)34+","+(char)34+"coordinates"+(char)34+":[[");
		        for(int k=0;k<bottom.size();k++){
		        	sb.append("["+ Double.toString(73.5+grid_width*(min_col+k))+","+ Double.toString(18.1+grid_height*bottom.get(k))+"],");
		        	sb.append("["+ Double.toString(73.5+grid_width*(min_col+k+1))+","+ Double.toString(18.1+grid_height*bottom.get(k))+"],");
		        }
		        for(int k=0;k<right.size();k++){
		        	sb.append("["+ Double.toString(73.5+grid_width*(right.get(k)+1))+","+ Double.toString(18.1+grid_height*(bottom.get(max_col-min_col)+k))+"],");
		        	sb.append("["+ Double.toString(73.5+grid_width*(right.get(k)+1))+","+ Double.toString(18.1+grid_height*(bottom.get(max_col-min_col)+k+1))+"],");
		        }
		        for(int k=top.size()-1;k>-1;k--){
		        	sb.append("["+ Double.toString(73.5+grid_width*(min_col+k+1))+","+ Double.toString(18.1+grid_height*(top.get(k)+1))+"],");
		        	sb.append("["+ Double.toString(73.5+grid_width*(min_col+k))+","+ Double.toString(18.1+grid_height*(top.get(k)+1))+"],");
		        }
		        for(int k=left.size()-1;k>-1;k--){
		        	sb.append("["+ Double.toString(73.5+grid_width*(left.get(k)))+","+ Double.toString(18.1+grid_height*(bottom.get(0)+k+1))+"],");
		        	sb.append("["+ Double.toString(73.5+grid_width*(left.get(k)))+","+ Double.toString(18.1+grid_height*(bottom.get(0)+k))+"],");
		        }
		        bottom.clear();
		        top.clear();
		        left.clear();
		        right.clear();
		        sb.append("]]}}");
		        first = false;
        	} 	
        }
		sb.append("]");
		String strJson = new String(sb); 
		return strJson;
	}

		//public static void main(String[] args) throws IOException {
		//	String grid = GetClassPolygon.GetBoundIndex(4096);  
		//	System.out.println(grid);
	//}
}

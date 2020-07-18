package Data;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GridDensity {
	public static String GetDensityofGrid(int grid_num) throws ClassNotFoundException, SQLException, IOException{
		int[][] grid_density = new int[grid_num][grid_num];
		for(int i=0;i<grid_num;i++){
			for(int j=0;j<grid_num;j++){
				grid_density[i][j] = 0;
			}
		}
		List<String> rstream = ReadCSV.GetData();
		Double grid_width = (135.1-73.5)/grid_num;
		Double grid_height = (53.6-18.1)/grid_num;
		for(int i=0;i<rstream.size();i++){
			String lngstr = rstream.get(i).substring(0,rstream.get(i).lastIndexOf(","));
			String latstr = rstream.get(i).substring(rstream.get(i).lastIndexOf(",")+1,rstream.get(i).length());
			Double lng = Double.valueOf(lngstr.toString());
			Double lat = Double.valueOf(latstr.toString());
			int col = (int) Math.floor((lng-73.5)/grid_width);
			int raw = (int) Math.floor((lat-18.1)/grid_height);
			grid_density[raw][col] = grid_density[raw][col] + 1;
		} 
		
		StringBuffer sb = new StringBuffer();
	    boolean first = true;
        sb.append("[");
		for(int i=0;i<grid_num;i++){
			for(int j=0;j<grid_num;j++){
				if(grid_density[i][j]>3){
					String density = String.valueOf(grid_density[i][j]);
					String index = String.valueOf(i*grid_num+j);
			        if (!first) {
			        	sb.append(",");
			        }
			        sb.append("{");
			        sb.append( (char)34+"index"+(char)34 +":"+(char)34+index+(char)34+",");
			        sb.append((char)34+"density"+(char)34 +":"+(char)34+density+(char)34);
			        sb.append("}");
			        first = false;
				}
			}
		}
		sb.append("]");
		String strJson = new String(sb); 
		return strJson;
	}
/*	public static void main(String[] args) throws SQLException, ClassNotFoundException, SQLException, IOException {
		String grid = GridDensity.GetDensityofGrid(4096);  
		System.out.println(grid);
	}*/
}

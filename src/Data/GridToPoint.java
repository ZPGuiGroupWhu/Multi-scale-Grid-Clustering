package Data;

import java.util.ArrayList;
import java.util.List;

public class GridToPoint {
	public static List<String> Mapping(List<String> rstream,double grid_width,double grid_height,double min_lng, double min_lat,int[][] grid_region) {
		List<String> list_str = new ArrayList<String>();
		for(int i=0;i<rstream.size();i++){
			String lngstr = rstream.get(i).substring(0,rstream.get(i).lastIndexOf(","));
			String latstr = rstream.get(i).substring(rstream.get(i).lastIndexOf(",")+1,rstream.get(i).length());
			Double lng = Double.valueOf(lngstr.toString());
			Double lat = Double.valueOf(latstr.toString());
			int col = (int) Math.floor((lng-min_lng)/grid_width);
			int raw = (int) Math.floor((lat-min_lat)/grid_height);
			if(grid_region[raw][col]>0){
				list_str.add(lng+","+lat+","+grid_region[raw][col]);
			}
		}
		return list_str;
	}

}

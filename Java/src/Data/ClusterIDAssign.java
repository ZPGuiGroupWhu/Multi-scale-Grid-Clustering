package Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClusterIDAssign {
	public static int[][] AssignID(int[][] grid_region) {		
		int grid_num = grid_region.length;
		List<Integer> list = new ArrayList<Integer>();
		int mark = 1;
		for(int i=0;i<grid_num;i++){
			for(int j=0;j<grid_num;j++){
				if(grid_region[i][j]>0){
					if(list.contains(grid_region[i][j])==false){
						int temp = grid_region[i][j];
						list.add(temp);
						grid_region[i][j] = mark;
						mark++;
					}
					else{
						int index = list.indexOf(grid_region[i][j]) + 1;
						grid_region[i][j] = index;						
					}
				}
			}
		}
		int clus_num = list.size();
		int[] arr = new int[clus_num];
		for(int i=0;i<clus_num;i++) {
			arr[i] = i+1;
		}
		Random random = new Random();
		for(int i=0;i<clus_num;i++){
			int p = random.nextInt(i+1);
			int tmp = arr[i];
			arr[i] = arr[p];
			arr[p] = tmp;
		}
		for(int i=0;i<grid_num;i++){
			for(int j=0;j<grid_num;j++){
				if(grid_region[i][j]>0) {
					grid_region[i][j] = arr[grid_region[i][j]-1];
				}
			}
		}
		return grid_region;
	}
}

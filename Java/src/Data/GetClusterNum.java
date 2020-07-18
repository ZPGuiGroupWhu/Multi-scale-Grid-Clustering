package Data;

public class GetClusterNum {
	public static int MaxID(int[][] grid_region) {
		int grid_num = grid_region.length;
		int cluster_num = 0;
		for(int i=0;i<grid_num;i++){
			for(int j=0;j<grid_num;j++){
				if(grid_region[i][j]>cluster_num) {
					cluster_num = grid_region[i][j];
				}
			}
		}		
		return  cluster_num;
	}
	
	/*public static void main(String[] args){
		int[][] a = new int[2][2];
		a[0][0] = 8;
		a[0][1] = 0;
		a[1][0] = 300;
		a[1][1] = 4;
		System.out.println(GetClusterNum.MaxID(a));
	}*/
}

    package Data;

public class DensitySum {
	public static int[] TotalDensity(int clus_num,int[][] grid_density,int[][] grid_region) {
		int grid_num = grid_density.length;
		int[] density_sum = new int[clus_num];
		for(int i=0;i<clus_num;i++){
			density_sum[i] = 0;
		}

		for(int i=0;i<grid_num;i++){
			for(int j=0;j<grid_num;j++){
				if(grid_region[i][j]>0){
					density_sum[grid_region[i][j]-1]+=grid_density[i][j];
				}
			}
		} 		
		return density_sum;
	}
}

package Data;

public class SearchConRegion {
	public static int[][] SrchOBRgn(int[][] grid_density) {
		int grid_num = grid_density.length;
		int[][] grid_region = new int[grid_num][grid_num];
		for(int i=0;i<grid_num;i++){
			for(int j=0;j<grid_num;j++){
				grid_region[i][j] = 0;
			}
		}
		int count = 1;
		for(int i=0;i<grid_num;i++){
			for(int j=0;j<grid_num;j++){
				if(grid_density[i][j]>0){
					if(i==0){
						if(j==0){
							grid_region[i][j] = count;
							count++;
						}
						else if(j>0){
							if(grid_density[i][j-1]>0){
								grid_region[i][j] = grid_region[i][j-1];
							}
							else{
								grid_region[i][j] = count;
								count++;
							}						
						}
					}
					else{
						if(j==0){
							if(grid_density[i-1][j]>0){
								grid_region[i][j] = grid_region[i-1][j];
							}
							else if(grid_density[i-1][j]==0&&grid_density[i-1][j+1]>0){
								grid_region[i][j] = grid_region[i-1][j+1];
							}
							else{
								grid_region[i][j] = count;
								count++;
							}
						}
						else if(j==grid_num-1){
							if(grid_density[i-1][j-1]>0){
								grid_region[i][j] = grid_region[i-1][j-1];
							}
							else if(grid_density[i-1][j-1]==0&&grid_density[i][j-1]==0&&grid_density[i-1][j]>0){
								grid_region[i][j] = grid_region[i-1][j];
							}
							else if(grid_density[i-1][j-1]==0&&grid_density[i-1][j]==0&&grid_density[i][j-1]>0){
								grid_region[i][j] = grid_region[i][j-1];
							}
							else if(grid_density[i-1][j-1]==0&&grid_density[i-1][j]>0&&grid_density[i][j-1]>0){
								grid_region[i][j] = grid_region[i-1][j];
								if(grid_region[i-1][j]!=grid_region[i][j-1]){
									int temp_region = grid_region[i][j-1];
									for(int k=0;k<i+1;k++){
										for(int r=0;r<grid_num;r++){
											if(grid_region[k][r]==temp_region){
												grid_region[k][r]=grid_region[i][j];
											}
										}
									}
								}
							}
							else{
								grid_region[i][j] = count;
								count++;
							}
						}
						else{
							if(grid_density[i-1][j]==0&&grid_density[i][j-1]==0&&grid_density[i-1][j-1]==0&&grid_density[i-1][j+1]==0){
								grid_region[i][j] = count;
								count++;
							}
							else if(grid_density[i-1][j]>0){
								grid_region[i][j] = grid_region[i-1][j];
							}
							else if(grid_density[i-1][j]==0&&grid_density[i][j-1]==0&&grid_density[i-1][j-1]==0&&grid_density[i-1][j+1]>0){
								grid_region[i][j] = grid_region[i-1][j+1];
							}
							else if(grid_density[i-1][j]==0&&grid_density[i][j-1]==0&&grid_density[i-1][j-1]>0&&grid_density[i-1][j+1]==0){
								grid_region[i][j] = grid_region[i-1][j-1];
							}
							else if(grid_density[i-1][j]==0&&grid_density[i][j-1]>0&&grid_density[i-1][j-1]==0&&grid_density[i-1][j+1]==0){
								grid_region[i][j] = grid_region[i][j-1];
							}
							else if(grid_density[i-1][j]==0&&grid_density[i][j-1]>0&&grid_density[i-1][j-1]>0&&grid_density[i-1][j+1]==0){
								grid_region[i][j] = grid_region[i-1][j-1];
								if(grid_region[i][j-1]!=grid_region[i-1][j-1]){
									int temp_region = grid_region[i][j-1];
									for(int k=0;k<i+1;k++){
										for(int r=0;r<grid_num;r++){
											if(grid_region[k][r]==temp_region){
												grid_region[k][r]=grid_region[i][j];
											}
										}
									}
								}
							}
							else if(grid_density[i-1][j]==0&&grid_density[i][j-1]>0&&grid_density[i-1][j-1]==0&&grid_density[i-1][j+1]>0){
								grid_region[i][j] = grid_region[i-1][j+1];
								if(grid_region[i][j-1]!=grid_region[i-1][j+1]){
									int temp_region = grid_region[i][j-1];
									for(int k=0;k<i+1;k++){
										for(int r=0;r<grid_num;r++){
											if(grid_region[k][r]==temp_region){
												grid_region[k][r]=grid_region[i][j];
											}
										}
									}
								}
							}
							else if(grid_density[i-1][j]==0&&grid_density[i][j-1]==0&&grid_density[i-1][j-1]>0&&grid_density[i-1][j+1]>0){
								grid_region[i][j] = grid_region[i-1][j-1];
								if(grid_region[i-1][j-1]!=grid_region[i-1][j+1]){
									int temp_region = grid_region[i-1][j+1];
									for(int k=0;k<i+1;k++){
										for(int r=0;r<grid_num;r++){
											if(grid_region[k][r]==temp_region){
												grid_region[k][r]=grid_region[i][j];
											}
										}
									}
								}
							}
							else if(grid_density[i-1][j]==0&&grid_density[i][j-1]>0&&grid_density[i-1][j-1]>0&&grid_density[i-1][j+1]>0){
								grid_region[i][j] = grid_region[i-1][j-1];
								if(grid_region[i-1][j-1]!=grid_region[i-1][j+1]){
									int temp_region = grid_region[i-1][j+1];
									for(int k=0;k<i+1;k++){
										for(int r=0;r<grid_num;r++){
											if(grid_region[k][r]==temp_region){
												grid_region[k][r] = grid_region[i][j];
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return grid_region;		
	}
	/*public static void main(String[] args){
	}*/
}

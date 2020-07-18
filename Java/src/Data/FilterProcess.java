package Data;

public class FilterProcess {
	public static int[][] Filtering(int bandwidth,int[][] grid_density) {
		int grid_num = grid_density.length;
		int[][] orig_density = grid_density;
		for(int i=bandwidth;i<grid_num-bandwidth;i++){
			for(int j=bandwidth;j<grid_num-bandwidth;j++){
				for(int r=i-bandwidth;r<i+bandwidth+1;r++) {
					for(int s=j-bandwidth;s<j+bandwidth+1;s++) {
						if(r==i&&s==j) {
							grid_density[i][j] = grid_density[i][j];		
						}	
						else {
							grid_density[i][j] += orig_density[r][s];
						}
					}
				}
				grid_density[i][j] = (int) grid_density[i][j]/((2*bandwidth+1)*(2*bandwidth+1));
			}
		}
		return grid_density;
	}
	/*public static void main(String[] args){
		int[][] a = new int[3][3];
		a[0][0] = 1;
		a[0][1] = 1;
		a[0][2] = 1;
		a[1][0] = 1;
		a[1][1] = 10;
		a[1][2] = 1;
		a[2][0] = 1;
		a[2][1] = 1;
		a[2][2] = 1;
		int[][] b = FilterProcess.Filtering(1, a);
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.println(b[i][j]);
			}
		}
	}*/
}

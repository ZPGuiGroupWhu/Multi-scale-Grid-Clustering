package Data;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ObservationScale {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException{
		
		long t0=System.currentTimeMillis();		
		int grid_num=512;         
		//Set the analytical scale (the number of grid cells)

		int T = 20;
		int[][] grid_density = new int[grid_num][grid_num];
		int[][] orig_density = new int[grid_num][grid_num];
		for(int i=0;i<grid_num;i++){
			for(int j=0;j<grid_num;j++){
				grid_density[i][j] = 0;
				orig_density[i][j] = 0;
			}
		}
		//Set the noise threshold and initialize the grid

		long t1=System.currentTimeMillis();		
		List<String> rstream = ReadCSV.GetData("C:\\Users\\Administrator\\Desktop\\数据\\全国企业POI.csv");
		//Read the raw data (data should be 2 columns with no column title, the first column is longitude and another is latitude)

		long t2=System.currentTimeMillis();
		System.out.println("Input time: "+(t2-t1)+"ms");		
		Double grid_width = (135.1-73.5)/grid_num;
		Double grid_height = (53.6-18.1)/grid_num;  
		//Generate the grid using the maximum and minimum of the coordinates

		for(int i=0;i<rstream.size();i++){
			String lngstr = rstream.get(i).substring(0,rstream.get(i).lastIndexOf(","));
			String latstr = rstream.get(i).substring(rstream.get(i).lastIndexOf(",")+1,rstream.get(i).length());
			Double lng = Double.valueOf(lngstr.toString());
			Double lat = Double.valueOf(latstr.toString());
			int col = (int) Math.floor((lng-73.5)/grid_width);
			int raw = (int) Math.floor((lat-18.1)/grid_height);
			grid_density[raw][col] = grid_density[raw][col] + 1;
		} 
		//Count the number of points in each grid cell

		for(int i=0;i<grid_num;i++){
			for(int j=0;j<grid_num;j++){
				if(grid_density[i][j] <T){
					grid_density[i][j] = 0; 
				}
			}
		}
		//Let the density of noisy cell be zero		

		int[][] grid_region = SearchConRegion.SrchOBRgn(grid_density);
		grid_region = ClusterIDAssign.AssignID(grid_region);
		//Search all connected regions and assign the cluster id to cells

		List<String> res = GridToPoint.Mapping(rstream, grid_width, grid_height, 73.5, 18.1, grid_region);	
		String filepath = "C:\\Users\\Administrator\\Desktop\\MVSC_res\\"+grid_num+".csv";
		WriteCSV.WriteData(filepath, res);	
		//Assign cluster id to points and write the result of MASC into a csv file
		
		long t3=System.currentTimeMillis();
		System.out.println("MASC time: "+(t3+t1-t2-t0)+"ms");		
		int clus_num = GetClusterNum.MaxID(grid_region);
		int[] density_sum = DensitySum.TotalDensity(clus_num, grid_density, grid_region);
		//Count the sum of points of each cluster
		
		List<Integer> level = MultiLevelExtraction.ExtractLvl(density_sum);
		//Extract the number of visual scales and threshold of each visual scale
		
		long output_time = 0;
		int[][] copy_grid_density = new int[grid_num][grid_num];
		for(int i=level.size()-1;i>=0;i--) {
			for(int j=0;j<grid_num;j++) {
				for(int k=0;k<grid_num;k++) {
					copy_grid_density[j][k] = grid_density[j][k];
				}
			}						
			for(int j=0;j<grid_num;j++) {
				for(int k=0;k<grid_num;k++) {
					if(grid_region[j][k]>0&&density_sum[grid_region[j][k]-1]<=level.get(i)) {
						copy_grid_density[j][k]=0;
					}
				}
			}
			//Select the clusters whose densities are greater than the threshold of each visual scale
			
			copy_grid_density = FilterProcess.Filtering(level.size()-i-1, copy_grid_density);
			//Filtering the selected clusters with variable-lengths templates
			
			int[][] new_region = SearchConRegion.SrchOBRgn(copy_grid_density);
			new_region = ClusterIDAssign.AssignID(new_region);
			//Reconstruct the connected regions and assign the cluster id to grid cells
			
			List<String> list_str = GridToPoint.Mapping(rstream, grid_width, grid_height, 73.5, 18.1, new_region);
			//Assign the cluster id to points
			
			long t4=System.currentTimeMillis();
			String filePath = "C:\\Users\\Administrator\\Desktop\\MVSC_res\\"+grid_num+"_"+(level.size()-i)+".csv";
			WriteCSV.WriteData(filePath, list_str);
			//Write results of MVSC at each visual scale into csv files
			
			long t5=System.currentTimeMillis();
			output_time += t5-t4;
		}
		
		long t6=System.currentTimeMillis();
		System.out.println("MVSC time: "+(t6-t3-output_time)+"ms");
		System.out.println("Output time: "+output_time+"ms");
		System.out.println("Sum time: "+(t6-t0)+"ms");
		//Output the running time

	}			
}

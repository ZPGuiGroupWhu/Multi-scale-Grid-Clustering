package Data;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
public class GetNoiseNum {
	public static int GetNum(int grid_num,int test_num) throws ClassNotFoundException, SQLException, IOException{
		int threshold = 0;
		int[] dif = new int[test_num-2];
		int[] noise_num = new int[test_num];
		for(int i=0;i<test_num;i++){
			noise_num[i] = 0;
		}
		int[][] grid_density = new int[grid_num][grid_num];
		for(int i=0;i<grid_num;i++){
			for(int j=0;j<grid_num;j++){
				grid_density[i][j] = 0;
			}
		}
		List<String> rstream = ReadCSV.GetData("C:\\Users\\Administrator\\Desktop\\hubei.csv");
		//Double grid_width = (135.1-73.5)/grid_num;
		//Double grid_height = (53.6-18.1)/grid_num;  //大陆企业
		//Double grid_width = (115.05-113.82)/grid_num;
		//Double grid_height = (31.31-29.97)/grid_num;   //手机app
		//Double grid_width = (110.18-105.3041)/grid_num;
		//Double grid_height = (32.17-28.18615)/grid_num;   //重庆企业
		Double grid_width = (116.12-108.42)/grid_num;
		Double grid_height = (33.24-29.07)/grid_num;    //武汉GPS
		for(int i=0;i<rstream.size();i++){
			String lngstr = rstream.get(i).substring(0,rstream.get(i).lastIndexOf(","));
			String latstr = rstream.get(i).substring(rstream.get(i).lastIndexOf(",")+1,rstream.get(i).length());
			Double lng = Double.valueOf(lngstr.toString());
			Double lat = Double.valueOf(latstr.toString());
			//int col = (int) Math.floor((lng-73.5)/grid_width);
			//int raw = (int) Math.floor((lat-18.1)/grid_height);
			//int col = (int) Math.floor((lng-113.82)/grid_width);
			//int raw = (int) Math.floor((lat-29.97)/grid_height);
			//int col = (int) Math.floor((lng-105.30)/grid_width);
			//int raw = (int) Math.floor((lat-28.18)/grid_height);
			int col = (int) Math.floor((lng-108.42)/grid_width);
			int raw = (int) Math.floor((lat-29.07)/grid_height);
			grid_density[raw][col] = grid_density[raw][col] + 1;
		} 
		for(int k=1;k<test_num;k++){
			for(int i=1;i<grid_num-1;i++){
				if(grid_density[i][0]>0&&grid_density[i][0]<=k&&grid_density[i][1]<=k&&grid_density[i-1][0]<=k&&grid_density[i+1][0]<=k&&grid_density[i-1][1]<=k&&grid_density[i+1][1]<=k){
					noise_num[k] = noise_num[k] + grid_density[i][0]; 
				}
				if(grid_density[i][grid_num-1]>0&&grid_density[i][grid_num-1]<=k&&grid_density[i][grid_num-2]<=k&&grid_density[i-1][grid_num-1]<=k&&grid_density[i+1][grid_num-1]<=k&&grid_density[i-1][grid_num-2]<=k&&grid_density[i+1][grid_num-2]<=k){
					noise_num[k] = noise_num[k] + grid_density[i][grid_num-1];  
				}
				if(grid_density[0][i]>0&&grid_density[0][i]<=k&&grid_density[1][i]<=k&&grid_density[0][i-1]<=k&&grid_density[0][i+1]<=k&&grid_density[1][i-1]<=k&&grid_density[1][i+1]<=k){
					noise_num[k] = noise_num[k] + grid_density[0][i]; 
				}
				if(grid_density[grid_num-1][i]>0&&grid_density[grid_num-1][i]<=k&&grid_density[grid_num-2][i]<=k&&grid_density[grid_num-1][i-1]<=k&&grid_density[grid_num-1][i+1]<=k&&grid_density[grid_num-2][i-1]<=k&&grid_density[grid_num-2][i+1]<=k){
					noise_num[k] = noise_num[k] + grid_density[grid_num-1][i]; 
				}
			}
			for(int i=1;i<grid_num-1;i++){
				for(int j=1;j<grid_num-1;j++){
					if(grid_density[i][j]>0&&grid_density[i][j]<=k&&grid_density[i-1][j]<=k&&grid_density[i+1][j]<=k&&grid_density[i][j-1]<=k&&grid_density[i][j+1]<=k&&grid_density[i-1][j-1]<=k&&grid_density[i-1][j+1]<=k&&grid_density[i+1][j-1]<=k&&grid_density[i+1][j+1]<=k){
						noise_num[k] = noise_num[k] + grid_density[i][j];
					}
				}
			}
		}
		int min_dif = 100;
		for(int i=0;i<test_num-2;i++){
			System.out.println(noise_num[i+1]);
			dif[i] = noise_num[i]+noise_num[i+2]-2*noise_num[i+1];
			if(dif[i]<min_dif){
				min_dif = dif[i];
				threshold = i;
			}
		}
		threshold = threshold + 1;
		return threshold;
	}
	public static void main(String[] args) throws SQLException, ClassNotFoundException, SQLException, IOException {
		int cal_num = 100;
		int num;
		num = GetNoiseNum.GetNum(1024,cal_num);
		System.out.println(num);
	}
}

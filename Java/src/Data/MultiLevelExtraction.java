package Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MultiLevelExtraction {
	public static List<Integer> ExtractLvl(int[] sum) {
		int clus_num = sum.length;
		int[] density_sum = new int[clus_num];
		for(int i=0;i<clus_num;i++) {
			density_sum[i] = sum[i];
		}
		Arrays.sort(density_sum);   
		double[] crate = new double[clus_num-1];
		for (int i=0;i<clus_num-1;i++) {
			crate[i] = 1.0*density_sum[i+1]/density_sum[i]-1;
		}

		double mean_rate = Mean.GetMean(crate);
		double sd_rate =Math.sqrt(Variance.GetVariance(crate));
		
		int temp_level = density_sum[0]; 
		List<Integer> level = new ArrayList<Integer>();
		for (int i=1;i<clus_num;i++) {	
			temp_level += density_sum[i];
			if(crate[i-1]>mean_rate+3*sd_rate) {								
				level.add(temp_level-density_sum[i]);
				temp_level = density_sum[i];
			}
		}		
		level.add(temp_level);
		int iterations = 1;
		int lev_num;
		int temp_cluster;
		double ori_cv = VariableCoefficient.GetVC(level);
		double cv;
		for(int i=0;i<iterations;i++) {
			lev_num = level.size();
			for(int j=1;j<lev_num;j++) {
				temp_cluster = level.get(j-1);
				level.set(j, temp_cluster+level.get(j));
				level.set(j-1,0);
				cv = VariableCoefficient.GetVC(level);
				if(cv>ori_cv) {
					level.set(j-1,temp_cluster);
					level.set(j, level.get(j)-temp_cluster);
				}
				else{
					ori_cv = cv;
				}
			}
			level = RemoveZero.Remove(level);
		}
		List<Integer> level_gap = new ArrayList<Integer>();
		level_gap.add(0);
		 int temp_density;
		 int start_index = 0;
		 for(int i=0;i<level.size();i++) {
			 temp_density = 0;
			 for(int j=start_index;j<clus_num;j++) {
				temp_density += density_sum[j]; 
				if(temp_density==level.get(i)) {
					level_gap.add(density_sum[j]);
					start_index = j+1;
				}
			 }		  
		 }
		 int lvl_size = level_gap.size();
		 level_gap.remove(lvl_size-1);
		 return level_gap;
	}	
	public static void main(String[] args){
		int clus_num = 10;
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
		for(int i=0;i<clus_num;i++){
			System.out.println(arr[i]);
		}
	}
}

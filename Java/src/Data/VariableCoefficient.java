package Data;

import java.util.ArrayList;
import java.util.List;

public class VariableCoefficient {
	public static double GetVC(List<Integer> level){

		int size = level.size();
		double sum = 0;
		int nozero_num = 0;
		for(int i=0;i<size;i++){
			if(level.get(i)!=0) {
				nozero_num++;
				sum = sum + level.get(i);
			}
		}
		double ave_density;
		ave_density = 1.0*sum/nozero_num;
		
		double sd_density = 0;
		for(int i=0;i<size;i++){
			if(level.get(i)!=0) {
				sd_density += (level.get(i)-ave_density)*(level.get(i)-ave_density);
			}
		}
		sd_density = Math.sqrt(1.0*sd_density/nozero_num);
		
		double cv = sd_density/ave_density;
		return cv;
	}
	/*public static void main(String[] args){
		int[][] a = new int[6][2];
		System.out.println(a.length);
	}*/
}

package Data;

public class Variance {
	public static double GetVariance(double[] DensitySum){
		double variance;
		int size = DensitySum.length;
		double sum = 0;
		for(int i=0;i<size;i++){
			sum = sum + DensitySum[i];
		}
		double ave_density;
		ave_density = 1.0*sum/size;
		
		double temp = 0;
		for(int i=0;i<size;i++){
			if(DensitySum[i]>0){
				temp +=  1.0*(DensitySum[i]-ave_density)*(DensitySum[i]-ave_density);
			}
		}
		
		variance = temp/size;
		return variance;
	}
}

package Data;

public class Mean {
	public static double GetMean(double[] DensitySum){

		int size = DensitySum.length;
		double sum = 0;
		for(int i=0;i<size;i++){
			sum = sum + DensitySum[i];
		}
		double ave_density;
		ave_density = 1.0*sum/size;

		return ave_density;
	}
}

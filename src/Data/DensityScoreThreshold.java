package Data;

public class DensityScoreThreshold {
	public static double[] threshold(int[] ClassDensity){
		int class_num = ClassDensity.length;
		double[] change_rate = new double[class_num-1];
		int mark_num = 0 ;
		double sum_rate = 0;
		for(int i=0;i<class_num-1;i++){
			change_rate[i] = ((double)(ClassDensity[i+1]-ClassDensity[i])/ClassDensity[i+1]);
			sum_rate = sum_rate + change_rate[i];
			if(change_rate[i]>0){
				mark_num++;
			}
		}
		double ave_rate = (double)sum_rate/mark_num;
		double var_rate = Variance.GetVariance(change_rate);
		int big_num = 1;
		for(int i=0;i<class_num-1;i++){
			if(change_rate[i]>ave_rate+3*var_rate){
				big_num++;
			}
		}
		double[] DensitySum = new double[big_num];
		for(int i=0;i<big_num;i++){
			DensitySum[i] = 0;
		}
		int original_i = 0;
		for(int i=0;i<class_num-1;i++){
			if(change_rate[i]<ave_rate+3*var_rate){
				DensitySum[original_i] = DensitySum[original_i]+ClassDensity[i];
			}
			else{
				DensitySum[original_i] = DensitySum[original_i]+ClassDensity[i];
				original_i++;
			}
			
		}
		DensitySum[original_i] = DensitySum[original_i]+ClassDensity[class_num-1];
		double[] threshold = new double[big_num];
		/*for(int i=0;i<big_num;i++){
			threshold[i]=DensitySum[i];
		}*/
		double original_var = Variance.GetVariance(DensitySum);
	//	System.out.println(original_var);
	/*	DensitySum[1] = DensitySum[1]+DensitySum[0];
		DensitySum[0] = 0;
		double var1 = Variance.GetVariance(DensitySum);
		System.out.println(var1);*/
		double temp_density;
		double now_var;
		for(int i=big_num-1;i>0;i--){
			temp_density = DensitySum[i];
			DensitySum[i-1] = DensitySum[i] + DensitySum[i-1];
			DensitySum[i] = 0;
			now_var = Variance.GetVariance(DensitySum);
			if(now_var>original_var){
				DensitySum[i] = temp_density;
				DensitySum[i-1] = DensitySum[i-1] - temp_density;
			}
			else{
				original_var = now_var;
			}
		}
	//	System.out.println(original_var);
		for(int i=0;i<big_num;i++){
		threshold[i]=DensitySum[i];
	}
		return threshold;
	}

}

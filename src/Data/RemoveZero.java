package Data;

import java.util.ArrayList;
import java.util.List;

public class RemoveZero {
	public static List<Integer> Remove(List<Integer> level){

		int size = level.size();
		List<Integer> copy = new ArrayList<Integer>();
		for(int i=0;i<size;i++) {
			if(level.get(i)!=0) {
				copy.add(level.get(i));
			}
		}
		return copy;
	}
}

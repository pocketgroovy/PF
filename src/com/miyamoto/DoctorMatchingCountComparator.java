package com.miyamoto;

import java.util.Comparator;
import java.util.Map;

public class DoctorMatchingCountComparator implements Comparator<Doctor>{
	Map<Doctor, Integer> base;
	public DoctorMatchingCountComparator(Map<Doctor, Integer> map){
		base = map;
	}

	public int compare(Doctor a, Doctor b){
		if(base.get(a) >= base.get(b)){
			return -1;
		}
		else{
			return 1;
		}
	}

}
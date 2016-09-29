package com.miyamoto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.TreeMap;

public class DoctorFinder{
	private ArrayList<Doctor> _allDoctors = new ArrayList<>();


	public List<Doctor> findSimilarDoctors(Doctor doc){
			if(_allDoctors.size() == 1) return _allDoctors;
			if(_allDoctors.size() == 0){
				System.out.println("Any of Doctor Data Not found"); // this must be error: all data is missing
				return _allDoctors;	// could throw exception. UX wise just return empty list and deal it with backend
			} 
			List<Doctor> similarDoctors = getSimilarDoctorsInOrder(doc, _allDoctors);
			return similarDoctors; // if no doctor found, return empty list. the view can decide what to do with the empty list
	}

	public ArrayList<Doctor> getAllDoctors(){
		return _allDoctors;
	}

	// if we could have unique IDs for each doctor, it could prevent the same doctor registering multiple times
	public void registerDoctor(Doctor doc){ 
		_allDoctors.add(doc);
	}

	public ArrayList<Doctor> getSimilarDoctorsInOrder(Doctor docRef, List<Doctor> allDoctors){
		HashMap<Doctor, Integer> matchingCount = getMatchingCount(docRef, allDoctors);
		TreeMap<Doctor, Integer> sortedMap = sortMap(matchingCount);
		ArrayList<Doctor> docInOrder = new ArrayList<Doctor>(sortedMap.keySet()); // convert to array so that access it is easier
		return docInOrder;
	}

	final int EXTRA_PRIORITY = 2;
	public HashMap<Doctor, Integer> getMatchingCount(Doctor docRef, List<Doctor> allDoctors){
		HashMap<Doctor, Integer> matchingCount = new HashMap<>();
		for(Doctor doc : allDoctors){
			int count = 0;
			if(matchingCount.containsKey(doc)){
				count = matchingCount.get(doc);
			}
			if(docRef.getSpecialty() == doc.getSpecialty()){
				count++;

				if(docRef.getArea() == doc.getArea()){
					count+=EXTRA_PRIORITY; // added extra priority to area over others

				}
				if(docRef.getGender() == doc.getGender()){
					count++;
				}
				// if there is no common items in the list, it returns true
				if(!Collections.disjoint(docRef.getLanguages(), doc.getLanguages())){ 
					count++;
				}
				if(count != 0){ // only if there is any matching it wil make it to the list
					matchingCount.put(doc, count);
				}
			}
		}
		return matchingCount;
	}

	private TreeMap<Doctor, Integer> sortMap(HashMap<Doctor, Integer> countMap){
		DoctorMatchingCountComparator comparator = new DoctorMatchingCountComparator(countMap);
		TreeMap<Doctor, Integer> sortedMap = new TreeMap<Doctor, Integer>(comparator);
		sortedMap.putAll(countMap);
		return sortedMap;
	}
}
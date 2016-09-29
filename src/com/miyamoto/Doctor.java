package com.miyamoto;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class Doctor{
	private String name;
	private String specialty;
	private String area;
	private String gender;
	private String careerLength;
	private ArrayList<String> languages;
	private ArrayList<Integer> reviewScores;
	private double sum = -1;

	// assuming that a doctor is specialized in one category and practicing in only one location
	public Doctor(String name, String specialty, String area, String gender){
		this.name = name;
		this.specialty = specialty;		
		this.area = area;
		this.gender = gender == null ? "" : gender;
		languages = new ArrayList<>();
		reviewScores = new ArrayList<>();
	}

	public String getName(){
		return name;
	}

	public String getSpecialty(){
		return specialty;
	}

	public  String getArea(){
		return area;
	}

	public String getGender(){
		return gender;
	}

	public void setLanguage(String language){
		languages.add(language);
	}

	public ArrayList<String> getLanguages(){
		return languages;
	}

	// get tne sum here so that it will save time to return the average score when asked for it.
	// ideally set to and get it from database 
	public void setReviewScore(int score){
		reviewScores.add(score);
		if(sum != -1){
			sum += score;
		}
		else{
			sum = score;
		}
	}

	public  String getReviewScoreAverage(){
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		double averageScore = sum/reviewScores.size();
		return df.format(averageScore);
	}

	public String toString(){
		return "name: " + name + ", specialty: " + specialty +
		", area: " + area;
	}
}
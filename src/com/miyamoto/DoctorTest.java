package com.miyamoto;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorTest{
	
	@Test
	public void testGetName() throws Exception{
		Doctor doc = new Doctor("Tom", "Chiropractor", "Fremont", "Male");
		String name = doc.getName();
		assertEquals("Tom", name);
	}

	@Test
	public void testGetSpecialty() throws Exception{
		Doctor doc = new Doctor("Tom", "Chiropractor", "Fremont", "Male");
		String specialty = doc.getSpecialty();
		assertEquals("Chiropractor", specialty);
	}

	@Test
	public void testGetArea() throws Exception{
		Doctor doc = new Doctor("Tom", "Chiropractor", "Fremont", "Male");
		String area = doc.getArea();
		assertEquals("Fremont", area);
	}

	@Test
	public void testGender() throws Exception{
		Doctor doc = new Doctor("Tom", "Chiropractor", "Fremont", "Male");
		String gender = doc.getGender();
		assertEquals("Male", gender);
	}

	@Test
	public void testGetLanguage() throws Exception{
		Doctor doc = new Doctor("Tom", "Chiropractor", "Fremont", "Male");
		doc.setLanguage("Portuguese");
		List<String> languages = doc.getLanguages();
		assertEquals("Portuguese", languages.get(0));
	}

	@Test
	public void testGetAvgReviewScore() throws Exception{
		Doctor doc = new Doctor("Tom", "Chiropractor", "Fremont", "Male");
		doc.setReviewScore(5);
		doc.setReviewScore(2);
		doc.setReviewScore(3);
		doc.setReviewScore(5);
		String score = doc.getReviewScoreAverage();
		assertEquals("3.75", score);
	}

}
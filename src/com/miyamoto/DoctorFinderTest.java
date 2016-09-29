package com.miyamoto;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.List;
import java.util.HashMap;

public class DoctorFinderTest{
	DoctorFinder docFinder;

	@Before
	public void setUp(){
		docFinder = new DoctorFinder();
		Doctor doc1 = new Doctor("Mary", "Pediatric", "Fremont", "Female");
		Doctor doc2 = new Doctor("Brian", "Acupuncture", "Oakland", "Male");
		Doctor doc3 = new Doctor("Cathy", "Pediatric", "San Francisco", "Female");
		Doctor doc4 = new Doctor("Peter", "Chiropractor", "San Francisco", "Male");
		Doctor doc5 = new Doctor("Graham", "Family Practice", "Fremont", "Male");
		Doctor doc6 = new Doctor("Adrian", "Dermatology", "San Jose", "Female");
		Doctor doc7 = new Doctor("Elisabeth", "Family Practice", "Oakland", "Female");
		docFinder.registerDoctor(doc1);
		docFinder.registerDoctor(doc2);
		docFinder.registerDoctor(doc3);
		docFinder.registerDoctor(doc4);
		docFinder.registerDoctor(doc5);
		docFinder.registerDoctor(doc6);
		docFinder.registerDoctor(doc7);
	}

	@Test
	public void testGetAllDoctors() throws Exception{
		List<Doctor> doctors = docFinder.getAllDoctors();
		assertEquals(7, doctors.size());
	}

	@Test
	public void testGetSimilarDoctorsInOrder() throws Exception{
		Doctor refDoc = new Doctor("", "Family Practice", "Fremont", "Female");
		List<Doctor> docs = docFinder.getSimilarDoctorsInOrder(refDoc, docFinder.getAllDoctors());
		assertEquals(2, docs.size());
		assertEquals("Family Practice", docs.get(0).getSpecialty());
		assertEquals("Family Practice", docs.get(1).getSpecialty());
		assertEquals("Male", docs.get(0).getGender());
		assertEquals("Female", docs.get(1).getGender());
		assertEquals("Fremont", docs.get(0).getArea());
		assertEquals("Oakland", docs.get(1).getArea());
	}

	@Test
	public void testGetMatchingCount(){
		Doctor refDoc = new Doctor("", "Dermatology", "Fremont", "Female");
		HashMap<Doctor, Integer> match = docFinder.getMatchingCount(refDoc, docFinder.getAllDoctors());
		assertTrue(match.containsValue(2));
	}

	@Test
	public void testFindSimilarDoctors() throws Exception{
		Doctor refDoc = new Doctor("", "Pediatric", "Oakland", "Female");
		List<Doctor> docs = docFinder.findSimilarDoctors(refDoc);
		assertEquals(2, docs.size());
		assertEquals("Pediatric", docs.get(0).getSpecialty());
		assertEquals("Pediatric", docs.get(1).getSpecialty());
		assertEquals("Female", docs.get(0).getGender());
		assertEquals("Female", docs.get(1).getGender());
	}

	@Test
	public void testFindSimilarDoctors_Not_Found() throws Exception{
		Doctor refDoc = new Doctor("", "dentist", "Oakland", "Female");
		List<Doctor> docs = docFinder.findSimilarDoctors(refDoc);
		assertTrue(docs.size()==0);
	}
}
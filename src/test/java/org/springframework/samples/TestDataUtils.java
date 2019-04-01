package org.springframework.samples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.samples.petclinic.model.PetType;

public class TestDataUtils{
	
	public static final int TEST_OWNER_ID = 1;
	
	
//	public static final PetType PETTYPE_DOG = "dog";
//	public static final String PETTYPE_CAT = "cat";
	
	/**
	 * "yyyy-MM-dd HH:mm:ss"
	 * @param dateString
	 * @return
	 * @throws ParseException 
	 */
	public static Date string2Date(String dateString, String dateFormat) throws ParseException {
		SimpleDateFormat transFormat = new SimpleDateFormat(dateFormat);
		return transFormat.parse(dateString);
	}
	
	public static Date string2Date(String dateString) throws ParseException {
		return string2Date(dateString, "yyyy-MM-dd");
	}
	
	/**
	 * 디폴트 포멧 "yyyy-MM-dd"
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static LocalDate string2LocalDate(String dateString) throws ParseException {
		return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	/**
	 * 디폴트 포멧 "yyyy-MM-dd"
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static LocalDate string2LocalDate(String dateString, String formatString) throws ParseException {
		return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(formatString));
	}
	
	
	public static Date string2BirthDate(String birthDate) throws ParseException {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		return transFormat.parse(birthDate);
	}
	
	public static PetType getPetType(String petTypeString) throws TestSetupException {
		int petId = -1;
		String petTypeName = "";
		PetType petType = new PetType();
		switch(petTypeString.toLowerCase()) {
		case "dog":
			petId = 2;
			petTypeName = "dog";
			break;
		case "cat":
			petId = 1;
			petTypeName = "cat";
			break;
		default:
			throw new TestSetupException("Not (yet) defined petType - "+petTypeString);
		}
		petType.setId(petId);
		petType.setName(petTypeName);
		return petType;
	}
	

}

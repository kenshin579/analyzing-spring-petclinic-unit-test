package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.assertj.core.api.Assertions.*;

import java.util.Collection;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.samples.AbstractPetStoreTestCase;
import org.springframework.samples.TestDataUtils;
import org.springframework.samples.petclinic.common.PetClinicCoreException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
public class ClinicServiceTest extends AbstractPetStoreTestCase{
	private final int testOwnerId = 1;
   
	@Autowired
	ClinicService clinicService;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	@Transactional
	public void testSavePet_기본()  throws Exception {
        Owner testOwner = this.clinicService.findOwnerById(testOwnerId);
        assertNotNull(testOwner);
        
        int found = testOwner.getPets().size();

        Pet newPet = new Pet();
        newPet.setName("my test pet");
        Collection<PetType> types = this.clinicService.findPetTypes();
        newPet.setType(EntityUtils.getById(types, PetType.class, 2));
        newPet.setBirthDate(TestDataUtils.string2LocalDate("2018-12-01"));
        testOwner.addPet(newPet);
        assertNull(newPet.getId());
        assertEquals(found+1, testOwner.getPets().size());
//        assertThat(testOwner.getPets().size()).isEqualTo(found + 1);

        this.clinicService.savePet(newPet);
        this.clinicService.saveOwner(testOwner);
        
        testOwner = this.clinicService.findOwnerById(6);
        assertEquals(found+1, testOwner.getPets().size());
        // checks that id has been generated
//        assertNotNull(newPet.getId());
        assertThat(newPet.getId()).isNotNull();
	}
	
	@Test
	@Transactional
	public void testSavePet_JDBCTemplate이용()  throws Exception {
		int testOwnerId = jdbcTemplate.queryForObject("select id from owners LIMIT 1;", Integer.class);
		
        Owner testOwner = this.clinicService.findOwnerById(testOwnerId);
        assertNotNull(testOwner);
        
        int found = testOwner.getPets().size();

        Pet newPet = new Pet();
        newPet.setName("my test pet");
        Collection<PetType> types = this.clinicService.findPetTypes();
        newPet.setType(EntityUtils.getById(types, PetType.class, 2));
        newPet.setBirthDate(TestDataUtils.string2LocalDate("2018-12-01"));
        testOwner.addPet(newPet);
        assertNull(newPet.getId());
        assertEquals(found+1, testOwner.getPets().size());
//        assertThat(testOwner.getPets().size()).isEqualTo(found + 1);

        this.clinicService.savePet(newPet);
        this.clinicService.saveOwner(testOwner);
        
        testOwner = this.clinicService.findOwnerById(testOwnerId);
        assertEquals(found+1, testOwner.getPets().size());
        // checks that id has been generated
        assertNotNull(newPet.getId());
//        assertThat(pet.getId()).isNotNull();
	}
	
	@Test(expected=PetClinicCoreException.class)
	public void testSavePet_pet이Null인경우()  throws Exception {
        Pet newPet = null;
        this.clinicService.savePet(newPet);
        fail("기대한 Exception이 발생하지 않았습니다");
	}
	
}

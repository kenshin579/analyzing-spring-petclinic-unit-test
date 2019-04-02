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
	    //사전동작 1. 테스트에 사용할 임의의 Owner 정보조회
        Owner testOwner = this.clinicService.findOwnerById(testOwnerId);
        assertNotNull(testOwner);

        //사전동작 2. 테스트에 사용할 임의의 애완동물 타입 조회 : DOG
        Collection<PetType> types = this.clinicService.findPetTypes();
        PetType testPetType = EntityUtils.getById(types, PetType.class, 2);

        //1. 기능 수행 전 해당 Owner의 애완동물 수 확인
        int found = testOwner.getPets().size();

        //2. 등록할 임의의 애완동물 정보 설정하여 Owner에 추가
        Pet newPet = new Pet();
        newPet.setName("my test pet");
        newPet.setType(testPetType);
        newPet.setBirthDate(TestDataUtils.string2LocalDate("2018-12-01"));
        testOwner.addPet(newPet);
        assertNull(newPet.getId());// 등록 전 id값 null 확인
        assertEquals(found+1, testOwner.getPets().size());
//        assertThat(testOwner.getPets().size()).isEqualTo(found + 1);  //AssertJ 확인 문법 비교

        //3. 애완동물 저장
        this.clinicService.savePet(newPet);
        //this.clinicService.saveOwner(testOwner);

        //4. Owner 정보를 재조회하여 size+1 확인 등 검증
        testOwner = this.clinicService.findOwnerById(6);
        assertEquals(found+1, testOwner.getPets().size());
        // checks that id has been generated
//        assertNotNull(newPet.getId());
        assertThat(newPet.getId()).isNotNull(); //AssertJ 확인 문법 비교
	}

    @Test
    @Transactional
    public void testSaveOwner_기본()  throws Exception {
	    //0.테스트용 데이터 세팅
	    String testLastName = "";
        Owner testOwner = new Owner();
        testOwner.setLastName(testLastName);
        //TODO  testOwner.set....

        //1.등록 전 건수 조회
        Collection<Owner> prevOwnerResults = this.clinicService.findOwnerByLastName("");
        int found = prevOwnerResults.size();

        //2.등록 수행
        clinicService.saveOwner(testOwner);

        //3.등록 후 건수 조회
        //TODO  검증 구문 작성

    }

	@Test
	@Transactional
	public void testSavePet_JDBCTemplate이용()  throws Exception {
        //사전동작 1. 테스트에 사용할 임의의 Owner ID 를 쿼리문을 이용하여 조회
		int testOwnerId = jdbcTemplate.queryForObject("select id from owners LIMIT 1;", Integer.class);
		
        Owner testOwner = this.clinicService.findOwnerById(testOwnerId);
        assertNotNull(testOwner);

        //사전동작 2. 테스트에 사용할 임의의 애완동물 타입 조회 : CAT
        Collection<PetType> types = this.clinicService.findPetTypes();
        PetType testPetType = EntityUtils.getById(types, PetType.class, 1);

        //1. 기능 수행 전 해당 Owner의 애완동물 수 확인
        int found = testOwner.getPets().size();

        //2. 등록할 임의의 애완동물 정보 설정하여 Owner에 추가
        Pet newPet = new Pet();
        newPet.setName("my test pet2");
        newPet.setType(testPetType);
        newPet.setBirthDate(TestDataUtils.string2LocalDate("2018-12-01"));
        testOwner.addPet(newPet);
        assertNull(newPet.getId());

        //3. 애완동물 저장
        this.clinicService.savePet(newPet);
        //this.clinicService.saveOwner(testOwner);

        //4. Owner 정보를 재조회하여 size+1 확인 등 검증
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

	//TODO  public void saveVisit(Visit visit) 에 대한 테스트를 추가한다
    //TODO  public Pet findPetById(int id)  에 대한 테스트를 추가한다
    //TODO  public Collection<Vet> findVets()   에 대한 테스트를 추가한다
    //TODO  public Collection<Visit> findVisitsByPetId(int petId)   에 대한 테스트를 추가한다

}

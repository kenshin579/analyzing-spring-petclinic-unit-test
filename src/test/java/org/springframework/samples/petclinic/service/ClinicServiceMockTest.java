package org.springframework.samples.petclinic.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.samples.AbstractPetStoreTestCase;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/business-config.xml" })
@ActiveProfiles("jdbc")
public class ClinicServiceMockTest extends AbstractPetStoreTestCase {

	@InjectMocks
	ClinicServiceImpl clinicService;

	@Mock
	PetRepository petRepositry;

	@Mock
	VetRepository vetRepository;

	@Mock
	OwnerRepository ownerRepository;

	@Mock
	VisitRepository visitRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Transactional
	public void testFindPetTypes_기본() throws Exception {
		//0.Mock을 이용해 임의의 petType을 반환하도록 사전 설정
		when(petRepositry.findPetTypes()).thenReturn(getMockPetTypeList());

		//1.테스트 실행
		//TODO	findPetTypes을 실행하고 값을 받아오는 코드를 작성

		clinicService.findPetTypes();

		//2.수행 결과가 예상결과와 같은지 확인하는 검증 구문 작성
		//TODO	수행결과를 확인하는 검증 코드 작성

		//Z.Mock이 제대로 적용되었는지(최소 1번 수행되었는지) 확인 구문
		verify(petRepositry, atLeastOnce()).findPetTypes();
	}

	@Test
	public void testFindPetTypes_DB에애완동물종류가없는경우() throws Exception {
		//0.Mock을 이용해 임의의 petType을 반환하도록 사전 설정
		when(petRepositry.findPetTypes()).thenReturn(null);

		//1.테스트 실행
		//TODO	findPetTypes을 실행하고 값을 받아오는 코드를 작성

		//2.수행 결과가 예상결과와 같은지 확인하는 검증 구문 작성
		//TODO	수행결과를 확인하는 검증 코드 작성

		//Z.Mock이 제대로 적용되었는지(최소 1번 수행되었는지) 확인 구문
		verify(petRepositry, atLeastOnce()).findPetTypes();
	}

	private List<PetType> getMockPetTypeList() {
		List<PetType> resultList = new ArrayList<PetType>();
		resultList.add(makePetType(1, "DOG"));
		resultList.add(makePetType(2, "CAT"));
		resultList.add(makePetType(3, "MONKEY"));
		return resultList;
	}

	private PetType makePetType(Integer id, String name) {
		PetType petType = new PetType();
		petType.setId(id);
		petType.setName(name);
		return petType;
	}

}

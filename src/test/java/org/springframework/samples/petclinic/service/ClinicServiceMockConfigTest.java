package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.samples.AbstractPetStoreTestCase;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.samples.petclinic.repository.jdbc.JdbcPetRepositoryImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/mock-config-clinicservice.xml", "classpath:spring/business-config.xml"})
@ActiveProfiles("jdbc")
public class ClinicServiceMockConfigTest extends AbstractPetStoreTestCase{
	@Autowired()
	PetRepository mockPetRepositry;
	
	@Autowired
	ClinicService clinicService;
	
	@Before
	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testFindPetTypes_DB에애완동물종류가없는경우()  throws Exception {
		when(mockPetRepositry.findPetTypes()).thenReturn(null);
		
		Collection<PetType> result = clinicService.findPetTypes();
		assertNull(result);
        verify(mockPetRepositry, atLeastOnce()).findPetTypes();
	}
}

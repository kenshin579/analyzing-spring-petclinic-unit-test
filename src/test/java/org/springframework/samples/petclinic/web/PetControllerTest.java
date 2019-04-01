package org.springframework.samples.petclinic.web;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.AbstractPetStoreControllerTestCase;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.web.PetController;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 */
public class PetControllerTest extends AbstractPetStoreControllerTestCase {

	private static final int TEST_OWNER_ID = 1;
	private static final int TEST_PET_ID = 1;

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Autowired
	PetController petController;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

    @Test
    public void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/pets/new", TEST_OWNER_ID))
            .andExpect(status().isOk())
            .andExpect(view().name("pets/createOrUpdatePetForm"))
            .andExpect(model().attributeExists("pet"));
    }

    @Test
    public void testProcessCreationForm_기본() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/pets/new", TEST_OWNER_ID)
            .param("name", "Betty")
            .param("type", "hamster")
            .param("birthDate", "2015/02/12")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/{ownerId}"));
    }
    
    @Test
    public void testProcessCreationForm_필수입력값type누락() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/pets/new", TEST_OWNER_ID)
            .param("name", "Betty")
//            .param("type", "hamster")
            .param("birthDate", "2015/02/12")
        )
            .andExpect(status().is2xxSuccessful())
            .andExpect(view().name("pets/createOrUpdatePetForm"));//요청 값이 잘못되었을 때 주인 페이지가 아닌 신규 등록 페이지로 다시 리다이렉트
            
    }
    
//    @Test
//    public void testInitUpdateForm() throws Exception {
//        mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/edit", TEST_OWNER_ID, TEST_PET_ID))
//            .andExpect(status().isOk())
//            .andExpect(model().attributeExists("pet"))
//            .andExpect(view().name("pets/createOrUpdatePetForm"));
//    }
//
//    @Test
//    public void testProcessUpdateFormSuccess() throws Exception {
//        mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/edit", TEST_OWNER_ID, TEST_PET_ID)
//            .param("name", "Betty")
//            .param("type", "hamster")
//            .param("birthDate", "2015/02/12")
//        )
//            .andExpect(status().is3xxRedirection())
//            .andExpect(view().name("redirect:/owners/{ownerId}"));
//    }
//
//    @Test
//    public void testProcessUpdateFormHasErrors() throws Exception {
//        mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/edit", TEST_OWNER_ID, TEST_PET_ID)
//            .param("name", "Betty")
//            .param("birthDate", "2015/02/12")
//        )
//            .andExpect(model().attributeHasNoErrors("owner"))
//            .andExpect(model().attributeHasErrors("pet"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("pets/createOrUpdatePetForm"));
//    }

}

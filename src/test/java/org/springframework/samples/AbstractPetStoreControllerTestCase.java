package org.springframework.samples;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ActiveProfiles("jdbc")
@ContextConfiguration({"classpath:spring/mvc-core-config.xml", "classpath:spring/business-config.xml"})
@WebAppConfiguration
public abstract class AbstractPetStoreControllerTestCase {

}

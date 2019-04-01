package org.springframework.samples;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@ActiveProfiles("jdbc")
public abstract class AbstractPetStoreTestCase {
}

//@ContextConfiguration({"classpath:spring/mvc-core-config.xml", "classpath:spring/mvc-test-config.xml"})
//@ContextConfiguration({"classpath:spring/mvc-core-config.xml"})
//@ContextConfiguration(locations = {"classpath:spring/business-config.xml", "classpath:spring/mvc-core-config.xml", "classpath:spring/mvc-view-config.xml"})





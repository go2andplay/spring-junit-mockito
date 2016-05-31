package com.trvajjala.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.trvajjala.Application;
import com.trvajjala.form.Registration;
import com.trvajjala.service.RegistrationService;

/**
 * If you use MockMvcBuilders.webAppContextSetup(this.applicationContext).build(). it try to create complete webAppContext using SpringJunitRunner and
 * Configuration classes that you provide
 *
 * http://docs.spring.io/spring-security/site/docs/current/reference/html/test-mockmvc.html This is not recommended approach as you can see it creates entire
 * webAppMockContext but doesn't work mockServices. and test case fails.
 *
 * @author ThirupathiReddy V
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class WebConfigRegistrationControllerTest {

    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @Mock
    RegistrationService registrationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Mockito.doNothing().when(this.registrationService).save(Mockito.any(Registration.class));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).build();

    }

    @Test
    public void invalidLoginFormTest() throws Exception {
        //@formatter:off
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/")
                        .param("username", "trvajjala")
                        .param("fullname", "SmallName")  //error-1
                        .param("email","invalidEmailID") //error-2
                        )
                .andDo(MockMvcResultHandlers.print())
                //without passing country code //error-3
                .andExpect(MockMvcResultMatchers.model().errorCount(3))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));

        //@formatter:on
    }

    @Test
    public void validLoginFormTest() throws Exception {

        // Mocking method call and saying that do nothing when save method is called on registrationService

        //@formatter:off
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/")
                        .param("username", "trvajjala")
                        .param("fullname", "ThirupathiReddyVajjala")
                        .param("email","trvajjala@gmail.com")
                        .param("country","IN")
                        )
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.status().isOk())
                //check fullname attribute available for for the view result
                .andExpect(MockMvcResultMatchers.model().attribute("fullname", "ThirupathiReddyVajjala"))
                .andExpect(MockMvcResultMatchers.view().name("result"));

        //@formatter:on

    }

}

package com.trvajjala.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.trvajjala.controller.RegistrationController;
import com.trvajjala.service.RegistrationService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = Application.class)
//@WebAppConfiguration
public class RegistrationControllerTest {

    @InjectMocks
    private RegistrationController registrationController;

    @Mock
    RegistrationService registrationService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.registrationController).build();
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
                //without passing country code //error-3
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.model().errorCount(3))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));

        //@formatter:on
    }

    @Test
    public void validLoginFormTest() throws Exception {
        //@formatter:off
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/")
                        .param("username", "trvajjala")
                        .param("fullname", "ThirupathiReddyVajjala")
                        .param("email","trvajjala@gmail.com")
                        .param("country","IN")
                        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.status().isOk())
                //check fullname attribute available for for the view result
                .andExpect(MockMvcResultMatchers.model().attribute("fullname", "ThirupathiReddyVajjala"))
                .andExpect(MockMvcResultMatchers.view().name("result"));

        //@formatter:on

    }

}

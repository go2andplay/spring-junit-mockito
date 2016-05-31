package com.trvajjala.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.trvajjala.controller.RegistrationController;
import com.trvajjala.form.Registration;
import com.trvajjala.service.RegistrationService;

public class DisplayRegDetailsTest {

    @InjectMocks
    private RegistrationController registrationController;

    @Mock
    RegistrationService registrationService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);// you can use MockitoJunitRunner instead of this
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.registrationController).build();
    }

    @Test
    public void invalidLoginFormTest() throws Exception {

        final Registration registration = new Registration();
        registration.setUsername("trvajjala");

        Mockito.when(this.registrationService.getByUsername("trvajjala")).thenReturn(registration);
        //@formatter:off
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/get-details")
                        .param("username", "trvajjala")
                        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.model().attribute("registration", registration))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("result"));

        //@formatter:on
    }

}

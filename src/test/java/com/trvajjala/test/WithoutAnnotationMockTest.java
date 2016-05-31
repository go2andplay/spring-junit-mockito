package com.trvajjala.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.trvajjala.form.Registration;
import com.trvajjala.service.RegistrationService;

/**
 * This is simple mockito service call test. but this won't work for mvc Controller testing.
 *
 * @author ThirupathiReddy V
 *
 */
public class WithoutAnnotationMockTest {

    // @Mock
    private RegistrationService registrationService;

    @Before
    public void setUp() {
        // MockitoAnnotations.initMocks(this);
        // registrationService = Mockito.mock(RegistrationService.class);
        registrationService = Mockito.spy(RegistrationService.class);
    }

    @Test
    public void mockServiceTest() throws Exception {

        Mockito.doNothing().when(registrationService).save(Mockito.any(Registration.class));

        registrationService.save(new Registration());
    }

}

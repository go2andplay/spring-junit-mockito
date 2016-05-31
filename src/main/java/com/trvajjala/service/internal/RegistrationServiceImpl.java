package com.trvajjala.service.internal;

import org.springframework.stereotype.Service;

import com.trvajjala.form.Registration;
import com.trvajjala.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public void save(Registration registration) {
        System.out.println(this);
        throw new RuntimeException("Unable to connect to Database.please check DB Server is up and running");
    }

    @Override
    public Registration getByUsername(String username) {

        return null;
    }
}

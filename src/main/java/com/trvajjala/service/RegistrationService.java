package com.trvajjala.service;

import com.trvajjala.form.Registration;

public interface RegistrationService {

    public void save(Registration registration);

    public Registration getByUsername(String username);
}

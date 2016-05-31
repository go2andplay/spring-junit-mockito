package com.trvajjala.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.trvajjala.form.Registration;
import com.trvajjala.service.RegistrationService;

/**
 *
 * @author ThirupathiReddy V
 *
 */
@Controller
public class RegistrationController {

    /** logger instance */
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationService registrationService;

    Map<String, String> countryMap = new HashMap<>();
    {
        this.countryMap.put("IN", "India");
        this.countryMap.put("US", "UnitedStates");
        this.countryMap.put("JP", "Japan");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute("registration", new Registration());
        model.addAttribute("countryMap", this.countryMap);
        LOG.info("Rendering default view from GET method");
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute Registration registration, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            LOG.warn("Validation failed. rendering registration form with error messages");
            model.addAttribute("countryMap", this.countryMap);
            return "index";
        }
        this.registrationService.save(registration);
        LOG.info("Registration successful. following details entered {} " + registration);
        model.addAttribute("fullname", registration.getFullname());

        return "result";
    }

    @RequestMapping(value = "/get-details", method = RequestMethod.GET)
    public String displayDetails(Model model, @RequestParam String username) {

        final Registration registration = this.registrationService.getByUsername(username);

        model.addAttribute("registration", registration);

        LOG.info("Returning registration details to view ");
        return "result";
    }

}

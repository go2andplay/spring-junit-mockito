package com.trvajjala.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.trvajjala.form.Registration;

/**
 * without using @ModelAttribute . compare with another controller
 *
 * @author ThirupathiReddy V
 *
 */
@Controller
public class RegistrationTypeTwoController {

    /** logger instance */
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String index(Registration registration) {

        LOG.info("Rendering default view from GET method");
        return "index";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String addNewPost(@Valid Registration registration, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            LOG.warn("Validation failed. rendering registration form with error messages");
            return "index";
        }
        LOG.info("Registration successful. following details entered {} " + registration);
        model.addAttribute("fullname", registration.getFullname());

        return "result";
    }

}

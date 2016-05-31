package com.trvajjala.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Java based configuration to configure message source and Validator
 *
 * @author ThirupathiReddy V
 *
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /** logger instance */
    private static final Logger LOG = LoggerFactory.getLogger(WebMvcConfig.class);

    private static final String MESSAGESOURCE_BASENAME = "classpath:messages";

    @Bean
    public MessageSource messageSource() {
        LOG.info("Creating messageSource with base properties file {} ", MESSAGESOURCE_BASENAME);
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(MESSAGESOURCE_BASENAME);
        messageSource.setDefaultEncoding("UTF-8");
        /** Below property useful when message key missing application won't crash.it display key directly as message */
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;

    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LOG.info("FormValidation ");
        final LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setValidationMessageSource(this.messageSource());
        return factoryBean;
    }

}

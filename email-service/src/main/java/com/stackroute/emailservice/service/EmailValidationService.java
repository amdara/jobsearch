package com.stackroute.emailservice.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import static com.stackroute.emailservice.util.Constants.EMAIL_REGEX;

@Service
public class EmailValidationService {
    Log log = LogFactory.getLog(EmailValidationService.class);

    /*
     * @Description: this method is used to validate a email
     * @Param: It takes String type as paramater
     * @returns : It returns a boolean value
     */
    public boolean isValidEmailAddress(String email) {
        log.info("Validating email...");
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(EMAIL_REGEX);
        java.util.regex.Matcher m = p.matcher(email);
        log.info("Validation done");
        return m.matches();
    }
}

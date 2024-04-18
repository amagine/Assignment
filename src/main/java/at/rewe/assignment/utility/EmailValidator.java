package at.rewe.assignment.utility;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final String REGEX_EMAIL_PATTERN = "^(.+)@(\\S+)$";

    public static boolean validate(String emailAddress) {
        return Pattern.compile(REGEX_EMAIL_PATTERN)
                .matcher(emailAddress)
                .matches();
    }
}

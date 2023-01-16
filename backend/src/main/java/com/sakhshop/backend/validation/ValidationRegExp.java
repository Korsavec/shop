package com.sakhshop.backend.validation;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationRegExp {

    private static final Pattern onlyLetters = Pattern.compile("^[a-zA-Z]+$");
    private static final Pattern onlyLettersCyrillic = Pattern.compile("^[а-яА-я]+$");
    private static final Pattern onlyNumbers = Pattern.compile("^[0-9]+$");
    private static final Pattern onlyLettersAndNumbers = Pattern.compile("^[0-9a-zA-Z]+$");
    private static final Pattern onlyLettersCyrillicAndNumbers = Pattern.compile("^[0-9а-яА-я]+$");

    private static final Pattern patternEmail = Pattern.compile("^(|(([A-Za-z0-9]{1,25}_)|([A-Za-z0-9]{1,25}-)|([A-Za-z0-9]{1,25}\\.))*[A-Za-z0-9]{1,25}@(([A-Za-z0-9]{1,25}\\.)|([A-Za-z0-9]{1,25}-))*[A-Za-z0-9]{2,25}\\.[a-zA-Z]{2,6})$");


    private static final Pattern patternPassword = Pattern.compile("^[0-9a-zA-Z@#$]+$");

    private static final Pattern patternDate = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20|29)\\d\\d$");



    //Pattern RegExp для проверки token-а
    private final static Pattern patternTokenConfirm = Pattern.compile("^([a-z0-9]{8})-([a-z0-9]{4})-([a-z0-9]{4})-([a-z0-9]{4})-([a-z0-9]{12})$");





    public boolean onlyNumbersRegExp(String phone) {

        Matcher matcherPhone = onlyNumbers.matcher(phone);

        return !matcherPhone.matches();
    }


    public boolean emailValidationRegExp(String email) {

        Matcher matcherEmail = patternEmail.matcher(email);

        return !matcherEmail.matches();
    }


    public boolean passwordValidationRegExp(String password) {

        Matcher matcherPassword = patternPassword.matcher(password);

        return !matcherPassword.matches();
    }

    public boolean onlyLettersRegExp(String username) {

        Matcher matcherPassword = onlyLetters.matcher(username);

        return !matcherPassword.matches();
    }

    public boolean onlyLettersCyrillic(String username) {

        Matcher matcherPassword = onlyLettersCyrillic.matcher(username);

        return !matcherPassword.matches();
    }



    public boolean validationTokenRegExp (String token) {

        Matcher matcher = patternTokenConfirm.matcher(token);

        return !matcher.matches();
    }


    public boolean validationDateRegExp (String date) {


        Matcher matcher = patternDate.matcher(date);

        return !matcher.matches();

    }


    public boolean validationOnlyLettersAndNumbersRegExp(String token) {

        Matcher matcher = onlyLettersAndNumbers.matcher(token);

        return !matcher.matches();
    }

    public boolean onlyLettersCyrillicAndNumbersRegExp(String token) {

        Matcher matcher = onlyLettersCyrillicAndNumbers.matcher(token);

        return !matcher.matches();
    }



}

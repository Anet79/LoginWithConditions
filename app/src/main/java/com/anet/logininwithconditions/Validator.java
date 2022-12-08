package com.anet.logininwithconditions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
Secure Password requirements:

-Password must contain at least one digit [0-9].
-Password must contain at least one lowercase Latin character [a-z].
-Password must contain at least one uppercase Latin character [A-Z].
-Password must contain at least one special character like ! @ # & ( ).
-Password must contain a length of at least 8 characters and a maximum of 20 characters.
 */
public class Validator {
    static boolean PatternUsername(String username){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(username);
        return matcher.find();
    }

    static boolean PatternPassword(String password){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}

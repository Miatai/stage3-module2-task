package com.mjc.school.controller.utils;

import java.util.Scanner;

import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.exceptions.ValidatorException;

public class Utils {
    public static long getKeyboardNumber(String params) {
        Scanner keyboard = new Scanner(System.in);
        long enter;
        try {
            enter = keyboard.nextLong();
            keyboard.nextLine();
        } catch (Exception ex) {
            keyboard.nextLine();
            throw new ValidatorException(
                    String.format(ServiceErrorCode.VALIDATE_INT_VALUE.getMessage(), params));
        }
        return enter;
    }

    public static String getKeyboardString(){
        Scanner keyboard = new Scanner(System.in);
        String str = keyboard.nextLine();
        return str;
    }
}

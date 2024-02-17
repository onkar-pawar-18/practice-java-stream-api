package com.streamapi;

import java.util.function.Predicate;

public class _Predicate {

    public static void main(String[] args) {

        System.out.println(isValid("7900000000"));

        /*Predicate<String> isValid = new Predicate<String>() {
            @Override
            public boolean test(String number) {
                return number.startsWith("79") && number.length() == 10;
            }
        };*/

        Predicate<String> isValid = number -> number.startsWith("79") && number.length() == 10;
        System.out.println(isValid.test("7900000000"));

        Predicate<String> contains = number -> number.contains("3");

        //Combine two predicates together
        System.out.println(isValid.or(contains).test("7900000003"));
        System.out.println(isValid.and(contains).test("7900000002"));
    }

    public static boolean isValid(String number) {
        return number.startsWith("79") && number.length() == 10;
    }
}

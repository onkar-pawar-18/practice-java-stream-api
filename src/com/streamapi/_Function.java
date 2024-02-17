package com.streamapi;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Function {

    public static void main(String[] args) {

        /*Function<Integer, Integer> addOne = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer+1;
            }
        };*/

        /*Function<Integer, Integer> multiplyByTen = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 10;
            }
        };*/

        //Function takes one argument and produces 1 output
        Function<Integer, Integer> addOne = integer -> integer + 1;
        Function<Integer, Integer> multiplyByTen = integer -> integer * 10;

        Function<Integer, Integer> combine = addOne.andThen(multiplyByTen);

        /*BiFunction<Integer, Integer, Integer> addAndMultiply = new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return (integer + 1) * integer2;
            }
        };*/

        //BiFunction takes two arguments and produces 1 output
        BiFunction<Integer, Integer, Integer> addAndMultiply = (integer, integer2) -> (integer + 1) * integer2;

        System.out.println(addOne.apply(1));
        System.out.println(multiplyByTen.apply(10));
        System.out.println(combine.apply(5));
        System.out.println(addAndMultiply.apply(5, 10));

    }

}

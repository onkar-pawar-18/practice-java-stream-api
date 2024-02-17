package com.streamapi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PracticeStream {

    public static void main(String[] args) {

        //Given a list of integers, filter out the prime numbers and calculate their sum.
        List<Integer> integers = Arrays.asList(5, 6, 9, 7, 42, 3, 9, 11, 13);
        double sum = integers.stream()
                .filter(integer ->
                        IntStream.range(2, integer).noneMatch(value -> integer % value == 0)
                )
                .mapToDouble(Integer::doubleValue).sum();
        System.out.println(sum);

        //Write a program that partitions a list of integers into two groups - even and odd numbers.
        Map<Boolean, List<Integer>> collect = integers.stream()
                .collect(Collectors.partitioningBy(integer -> integer % 2 == 0));
        System.out.println(collect);

        //Write a program that calculates the factorial of a given number using the Stream API.
        Integer num = 5;
        int reduce = IntStream.range(1, num + 1).reduce(1, (left, right) -> left * right);
        System.out.println(reduce);

        //Given a list of names, group them by the first letter and count the occurrences of each letter.
        List<String> strings = Arrays.asList("Onkar", "Onkya", "Pawar", "Alex", "Andres");
        Map<Character, Long> collect1 = strings.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0),
                        Collectors.mapping(String::chars, Collectors.counting())));
        System.out.println(collect1);

        //Write a program that combines multiple lists into a single list and sorts them in descending order.
        List<Integer> integerList1 = Arrays.asList(1, 5, 3, 6, 9, 4, 7);
        List<Integer> integerList2 = Arrays.asList(2, 10, 9, 8);
        List<Integer> collect2 = Stream.concat(integerList1.stream(), integerList2.stream()).distinct().sorted().collect(Collectors.toList());
        System.out.println(collect2);

        //Write a program that finds and prints duplicates from a list of integers using the Stream API.
        List<Integer> integers1 = Arrays.asList(1, 6, 8, 5, 2, 3, 6, 4, 7, 9, 10, 1, 2, 6);
        List<Integer> collect3 = integers1.stream()
                .collect(Collectors.groupingBy(Function.identity()))
                .entrySet().stream()
                .filter(integerListEntry -> integerListEntry.getValue().size() == 2)
                .map(integerListEntry -> integerListEntry.getKey())
                .collect(Collectors.toList());
        System.out.println(collect3);
    }

}

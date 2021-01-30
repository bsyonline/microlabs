/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.java8.stream;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author rolex
 * @since 2020
 */
public class StreamExample {
    public static void main(String[] args) {
        // collect
        List<Integer> collect1 = Stream.of(1, 2, 3).collect(Collectors.toList());
        System.out.println("collect1=" + collect1);
        // map
        List<String> collect2 = Stream.of("a", "b", "c").map(s -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println("collect2=" + collect2);
        // filter
        List<Integer> collect3 = Stream.of(1, 2, 3).filter(s -> s > 2).collect(Collectors.toList());
        System.out.println("collect3=" + collect3);
        // flatMap
        List<String> collect4 = Stream.of(Stream.of("a", "b", "c").collect(Collectors.toList()), Stream.of("d", "e", "f").collect(Collectors.toList()), Stream.of("g", "h", "i").collect(Collectors.toList()))
                .flatMap(c -> c.stream().map(s -> s.toUpperCase())).collect(Collectors.toList());
        System.out.println("collect4=" + collect4);
        // max/min
        Integer v1 = Stream.of(1, 2, 3).max(Comparator.comparing(c -> c)).get();
        System.out.println("v1=" + v1);
        // reduce
        Integer v2 = Stream.of(1, 2, 3).reduce(0, (x, e) -> x + e);
        System.out.println("v2=" + v2);
        // maxBy/minBy
        Integer v3 = Stream.of(1, 2, 3).collect(Collectors.maxBy(Comparator.comparing(i -> i))).get();
        System.out.println("v3=" + v3);
        // partitioningBy
        Map<Boolean, List<Integer>> collect5 = Stream.of(1, 2, 3).collect(Collectors.partitioningBy(i -> i % 2 == 0));
        System.out.println("collect5=" + collect5);
        // joining
        String v4 = Stream.of("a", "b", "c").collect(Collectors.joining(",", "", ""));
        System.out.println("v4=" + v4);
        // groupingBy+counting
        Map<Integer, Long> collect6 = Stream.of(1, 2, 3, 2, 3, 3).collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        System.out.println("collect6=" + collect6);
        // groupingBy+mapping
        Map<Integer, List<Integer>> collect7 = Stream.of(1, 2, 3, 2, 2, 3).collect(Collectors.groupingBy(i -> i, Collectors.mapping(i -> i, Collectors.toList())));
        System.out.println("collect7=" + collect7);
        // custom collector, like joining
        String v5 = Stream.of("a", "b", "c").collect(new StringCollector(", ", "[", "]"));
        System.out.println("v5=" + v5);
        /*
            map to list
         */
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        List<Integer> collect = map.entrySet().stream().map(kv -> kv.getKey()).collect(Collectors.toList());
        System.out.println(collect);

        /*
            list to map
         */
        Map<Integer, Integer> collectx = collect.stream().collect(Collectors.toMap(k -> k, k -> k));
        System.out.println(collect1);


    }

}

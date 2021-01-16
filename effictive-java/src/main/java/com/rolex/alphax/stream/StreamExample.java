/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.stream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author rolex
 * @since 2020
 */
public class StreamExample {
    public static void main(String[] args) {

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
        Map<Integer, Integer> collect1 = collect.stream().collect(Collectors.toMap(k -> k, k -> k));
        System.out.println(collect1);


    }
}

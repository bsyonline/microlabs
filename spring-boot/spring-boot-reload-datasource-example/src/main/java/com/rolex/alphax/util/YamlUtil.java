/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.util;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author rolex
 * @since 2020
 */
public class YamlUtil {

    private static Map<String, Object> ymlMap;

    static {
        ymlMap = new ConcurrentHashMap<>();
        getApplicationYml();
    }

    /**
     *
     */
    public YamlUtil() {
        super();
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> getApplicationYml() {
        try {
            Yaml yaml = new Yaml();
            URL url = ClassLoader.getSystemResource("application.yml");
            if (url != null) {
                Map<String, Object> map = yaml.loadAs(new FileInputStream(url.getFile()), Map.class);
                switchToMap(null, map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ymlMap;
    }

    @SuppressWarnings("unchecked")
    private static void switchToMap(String myKey, Map<String, Object> map) {
        Iterator<String> it = map.keySet().iterator();
        myKey = myKey == null ? "" : myKey;
        String tmpkey = myKey;
        while (it.hasNext()) {
            String key = it.next();
            myKey = tmpkey + key;
            Object value = map.get(key);
            if (value instanceof Map) {
                switchToMap(myKey.concat("."), (Map<String, Object>) value);
            } else {
                if (null != value) {
                    ymlMap.put(myKey, value);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) ymlMap.get(key);
    }

    public static String getStr(String key) {
        return String.valueOf(ymlMap.get(key));
    }

    public static Map<String, Map<String, Object>> lookupByPrefix(String prefix) {
        Map<String, Object> yaml = getApplicationYml();
        Map<String, Map<String, Object>> map = yaml.entrySet()
                .stream()
                .filter(kv -> (kv.getKey()).startsWith(prefix)
                        && "name".equals(kv.getKey().substring(kv.getKey().lastIndexOf(".") + 1)))
                .collect(Collectors.toMap(
                        l -> String.valueOf(l.getValue()),
                        l -> new ConcurrentHashMap<String, Object>(16))
                );
        yaml.entrySet()
                .stream()
                .filter(kv -> kv.getKey().startsWith(prefix))
                .collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()))
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(kv -> kv.getKey().substring(0, kv.getKey().lastIndexOf("."))))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        k -> k.getKey(),
                        v -> v.getValue()
                                .stream()
                                .collect(Collectors.toMap(
                                        o -> o.getKey().substring(o.getKey().lastIndexOf(".") + 1),
                                        o -> o.getValue())))
                ).values().forEach(v -> map.get(v.get("name")).putAll(v));
        return map;
    }

    public static void main(String[] args) {
        System.out.println(lookupByPrefix("spring.datasource."));
    }

}

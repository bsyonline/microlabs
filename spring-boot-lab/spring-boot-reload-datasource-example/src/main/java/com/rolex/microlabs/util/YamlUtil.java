/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.util;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
        Map<String, Map<String, Object>> map = new ConcurrentHashMap<>();
        for (Map.Entry<String, Object> kv : getApplicationYml().entrySet()) {
            String key = kv.getKey();
            if(key.startsWith(prefix)){
                String subStr = key.replace(prefix,"");
                int index = subStr.indexOf(".");
                String name = subStr.substring(0, index);
                Map<String, Object> m = new ConcurrentHashMap<>();
                if(map.get(name)==null){
                    m.put(subStr.substring(index+1), (String)kv.getValue());
                    map.put(name, m);
                }else{
                    map.get(name).put(subStr.substring(index+1), kv.getValue());
                }
            }
        }
        for (Map.Entry<String, Map<String, Object>> kv : map.entrySet()) {
            System.out.println(kv.getKey() + " - " + kv.getValue());
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(lookupByPrefix("spring.datasource."));
    }

}

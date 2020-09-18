package com.rolex.microlabs.service;

/**
 * @author rolex
 * @since 2020
 */
public interface HyperLogLogOpsService {


    void add(String key, String val);

    void merge(String key, String... keys);

    Long count(String key);
}

package com.rolex.microlabs.config;

/**
 * @author rolex
 * @since 2020
 */
public enum DataSourceType {
    DS1, DS2, DS3
    ;
    public static DataSourceType nameOf(String ds){
        for (DataSourceType value : DataSourceType.values()) {
            if(value.name().toLowerCase().equals(ds.toLowerCase())){
                return value;
            }
        }
        return DS1;
    }
}

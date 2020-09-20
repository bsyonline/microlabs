/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

/**
 * @author rolex
 * @since 2020
 */
public class Mapper {
    public void run(MapInputFormat mapInputFormat, MapOutputFormat mapOutputFormat) {
        setup(mapInputFormat, mapOutputFormat);
        try {
            while (mapInputFormat.nextKeyValue()) {
                map(mapInputFormat.getCurrentKey(), mapInputFormat.getCurrentValue, mapOutputFormat);
            }
        }catch (Exception e){

        }finally {
            cleanup(mapInputFormat, mapOutputFormat);
        }
    }

    private void setup(MapInputFormat mapInputFormat, MapOutputFormat mapOutputFormat) {

    }
}

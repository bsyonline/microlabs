package com.rolex.microlabs.structural.bridge.my;

/**
 * @author rolex
 * @since 2020
 */
public interface Importer {

    void parse();

    void transfer();

    void doImport();

    Strategy getStrategy();
}

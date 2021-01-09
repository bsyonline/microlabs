package com.rolex.microlabs.service;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.rolex.microlabs.model.User;

import java.util.List;

/**
 * @author rolex
 * @since 2021
 */
public interface UserService {

    List<User> getUser(@JsonRpcParam("name") String name);
}

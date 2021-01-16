package com.rolex.alphax.service;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.rolex.alphax.model.User;

import java.util.List;

/**
 * @author rolex
 * @since 2021
 */
@JsonRpcService("/users")
public interface UserService {

    List<User> getUser(@JsonRpcParam("name") String name);
}

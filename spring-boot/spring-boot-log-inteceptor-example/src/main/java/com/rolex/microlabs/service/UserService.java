/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service;

import com.rolex.microlabs.model.User;

/**
 * @author rolex
 * @since 2020
 */
public interface UserService {

    String get(Long id);
    String update(User user);

}

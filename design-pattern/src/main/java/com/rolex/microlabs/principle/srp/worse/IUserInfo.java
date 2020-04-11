/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.principle.srp.worse;

/**
 * @author rolex
 * @since 2020
 */
public interface IUserInfo {
    void setUserId(Integer userId);
    void setUserName(String userName);
    void setPassword(String password);
    void changePassword(String password);
}

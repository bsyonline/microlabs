package com.rolex.alphax.controller;

import com.rolex.alphax.service.bo.SysUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users/info")
    public SysUser info() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("John");
        return sysUser;
    }

}

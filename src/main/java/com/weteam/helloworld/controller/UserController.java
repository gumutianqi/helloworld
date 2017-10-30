/*
 * 四川生学教育科技有限公司
 * Copyright (c) 2015-2025 Founder Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Founder. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the agreements
 * you entered into with Founder.
 *
 */
package com.weteam.helloworld.controller;

import com.weteam.helloworld.domain.UserDomain;
import com.weteam.helloworld.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LarryKoo (gumutianqi@gmail.com)
 * @description UserController
 * @date 2017/10/30 01:19
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/user")
@Api(value = "User Controller", description = "User Controller description.")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "统计用户", notes = "统计用户", nickname = "user-count")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long count() {
        return userService.total();
    }

    @ApiOperation(value = "创建用户", notes = "创建用户", nickname = "user-create")
    @ApiImplicitParam(name = "userDomain", value = "用户对象", paramType = "body", dataType = "UserDomain", required = true)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Boolean create(@RequestBody UserDomain userDomain) {
        try {
            userService.save(userDomain);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        return true;
    }

    @ApiOperation(value = "创建用户", notes = "创建用户", nickname = "user-findByUser")
    @ApiImplicitParam(name = "name", value = "用户名", paramType = "query", dataType = "string", required = true)
    @RequestMapping(value = "/find_by_name", method = RequestMethod.GET)
    public List<UserDomain> findByUser(@RequestParam String name) {
        return userService.findByName(name);
    }
}

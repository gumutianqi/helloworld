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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: TODO
 * Created by LarryKoo (gumutianqi@gmail.com) on 2017/9/29 10:25
 * @author larrykoo
 */
@RestController
@RequestMapping(value = "/hello")
@Api(value = "Hello Controller", description = "Hello Controller description.")
public class HelloController {

    @ApiOperation(value = "返回字符串", notes = "返回一个固定的字符串", nickname = "hello-string")
    @RequestMapping(value = "/string", method = RequestMethod.GET)
    public String hello() {
        return "Hello World!";
    }
}

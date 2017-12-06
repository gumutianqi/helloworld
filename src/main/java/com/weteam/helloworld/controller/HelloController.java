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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Description: TODO
 * Created by LarryKoo (gumutianqi@gmail.com) on 2017/9/29 10:25
 *
 * @author larrykoo
 */
@Slf4j
@RestController
@RequestMapping(value = "/hello")
@Api(value = "Hello Controller", description = "Hello Controller description.")
public class HelloController {

    @Autowired
    private DbSearcher ipSearcher;

    @ApiOperation(value = "返回字符串", notes = "返回一个固定的字符串", nickname = "hello-string")
    @RequestMapping(value = "/string", method = RequestMethod.GET)
    public String hello() {
        return "Hello World!";
    }

    @ApiOperation(value = "查询 IP 地址归属地", notes = "查询 IP 地址归属地", nickname = "hello-ip")
    @ApiImplicitParam(name = "ip", value = "IP地址", paramType = "query", dataType = "string", required = true)
    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public String ip(@RequestParam(value = "ip") String ip) {
        try {
            return ipSearcher.memorySearch(ip).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

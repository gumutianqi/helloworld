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
package com.weteam.helloworld.service;

import com.weteam.helloworld.domain.UserDomain;

import java.util.List;

/**
 * @author LarryKoo (gumutianqi@gmail.com)
 * @description TODO
 * @date 2017/10/30 01:10
 * @since 1.0.0
 */
public interface UserService {

    Long total();

    void save(UserDomain user);

    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    List findByName(String name);
}

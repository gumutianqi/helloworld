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
package com.weteam.helloworld.service.impl;

import com.weteam.helloworld.dao.UserDao;
import com.weteam.helloworld.domain.UserDomain;
import com.weteam.helloworld.service.UserService;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LarryKoo (gumutianqi@gmail.com)
 * @description TODO
 * @date 2017/10/30 01:15
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Long total() {
        return userDao.allCount();
    }

    @Override
    public void save(UserDomain user) {
        userDao.insert(user);
    }

    @Override
    public List findByName(String name) {
        return userDao.sample(UserDomain.builder()
                .name(name).build());
    }
}

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
package com.weteam.helloworld.dao;

import com.weteam.helloworld.domain.UserDomain;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author LarryKoo (gumutianqi@gmail.com)
 * @description TODO
 * @date 2017/10/30 01:05
 * @since 1.0.0
 */
@SqlResource("user")
public interface UserDao extends BaseMapper<UserDomain> {

    /**
     * 使用默认的 user.sample
     *
     * @param condition 查询条件
     * @return List
     */
    List<UserDomain> sample(UserDomain condition);
}

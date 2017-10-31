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
package com.weteam.helloworld.config;

import org.beetl.sql.core.SQLManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author LarryKoo (gumutianqi@gmail.com)
 * @description BeetlSqlConfig
 * @date 2017/10/31 21:22
 * @since 1.0.0
 */
@Configuration
public class BeetlSqlConfig {

    private final static Logger log = LoggerFactory.getLogger(BeetlSqlConfig.class);

    @Autowired
    private SQLManager sqlManager;

    /**
     * 初始化BeetlSql
     */
    @PostConstruct
    public void init() {
        sqlManager.addIdAutonGen("uuid", params -> UUID.randomUUID().toString());
        sqlManager.setDefaultBeanProcessors(new LocalDateTimeBeanProcessor(sqlManager));

        log.info("[BeetlSQL Init] start");
    }
}

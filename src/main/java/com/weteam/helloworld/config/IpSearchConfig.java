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

import com.weteam.helloworld.HelloWorldApplication;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbMakerConfigException;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description TODO
 * @date 2017/12/6 21:12
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
@Slf4j
@Configuration
public class IpSearchConfig {

    /**
     * IP 查询算法选择
     */
    private static final int algorithm = DbSearcher.MEMORY_ALGORITYM;

    private static final String dbFilePath = "data/ip2region.db";

    /**
     * 初始化 IP 地址搜索对象
     *
     * @return
     * @throws DbMakerConfigException
     * @throws FileNotFoundException
     */
    @Bean
    public DbSearcher ipSearcher() throws DbMakerConfigException, FileNotFoundException {
        String dbFile = HelloWorldApplication.class.getClassLoader().getResource(dbFilePath).getPath();
        DbConfig config = new DbConfig();

        log.info("[IP-Address-Search-1] start.");
        return new DbSearcher(config, dbFile);
    }
}

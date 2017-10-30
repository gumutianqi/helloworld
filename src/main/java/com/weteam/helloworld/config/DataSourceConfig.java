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

import com.alibaba.druid.pool.DruidDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.test.MysqlDBConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author LarryKoo (gumutianqi@gmail.com)
 * @description DB 连接池初始化
 * @date 2017/10/30 02:18
 * @since 1.0.0
 */
@Configuration
public class DataSourceConfig {

    @Resource
    private DruidDataSource dataSource;

    @Bean("dataSource")
    public DataSource druidSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(MysqlDBConfig.url);
        ds.setUsername(MysqlDBConfig.userName);
        ds.setPassword(MysqlDBConfig.password);
        ds.setDriverClassName(MysqlDBConfig.driver);
        return ds;
    }


    @Bean
    public BeetlSqlDataSource beetlSqlDataSource() {
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(dataSource);
        return source;
    }

}

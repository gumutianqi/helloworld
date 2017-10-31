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
package com.weteam.helloworld.beetlsql;

import com.weteam.helloworld.config.LocalDateTimeBeanProcessor;
import com.weteam.helloworld.dao.UserDao;
import com.weteam.helloworld.domain.UserDomain;
import junit.framework.TestCase;
import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author LarryKoo (gumutianqi@gmail.com)
 * @description TODO
 * @date 2017/10/30 14:01
 * @since 1.0.0
 */
public class BeetlSQLDemo extends TestCase {

    private final static Logger log = LoggerFactory.getLogger(BeetlSQLDemo.class);

    private SQLManager sqlManager;

    @Override
    public void setUp() throws Exception {
        ConnectionSource source = ConnectionSourceHelper.getSimple(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/beetlsql?useunicode=true&useSSL=false&characterEncoding=utf8",
                "root", "root");
        DBStyle mysql = new MySqlStyle();
        // sql语句放在 classpath 的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion nc = new UnderlinedNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[]{new DebugInterceptor()});

        sqlManager.addIdAutonGen("uuid", params -> UUID.randomUUID().toString());

        sqlManager.setDefaultBeanProcessors(new LocalDateTimeBeanProcessor(sqlManager));
    }

    @Override
    public void tearDown() throws Exception {

    }

    public void testGenSQL() throws Exception {
        sqlManager.genSQLFile("user");
    }

    public void testCreateUser() {
        UserDomain user = UserDomain.builder()
                .name("koo")
                .age(28)
                .userName("LarryKoo_" + new Random().nextInt(100))
                .createDate(LocalDateTime.now())
                .build();

        Integer result = sqlManager.insert(user);
        Assert.assertTrue(1 == result);

        String id = user.getId();
        user = sqlManager.unique(UserDomain.class, id);
        Assert.assertTrue("koo".equals(user.getName()));
    }

    public void testUpdate() {
        UserDomain user = UserDomain.builder()
                .id("ed557624-164c-4e00-9f2f-f8dc45209646")
                .name("larry")
                .age(18)
                .build();

        Integer result = sqlManager.updateTemplateById(user);
        Assert.assertTrue(1 == result);

        UserDomain condition = UserDomain.builder()
                .name("larry")
                .build();

        user = sqlManager.templateOne(condition);
        Assert.assertTrue("larry".equals(user.getName()));
    }

    public void testMdSql() {
        UserDomain query = UserDomain.builder()
                .name("larry")
                .build();

        List<UserDomain> list = sqlManager.select("user.sample", UserDomain.class, query);
        Assert.assertTrue(list.size() == 1);
    }

    public void testDaoSql() {
        UserDao dao = sqlManager.getMapper(UserDao.class);
        List<UserDomain> list = dao.all();
        Assert.assertTrue("larry".equals(list.get(0).getName()));
    }

}
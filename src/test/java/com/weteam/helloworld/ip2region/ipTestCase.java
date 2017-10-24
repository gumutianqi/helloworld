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
package com.weteam.helloworld.ip2region;

import com.weteam.helloworld.HelloWorldApplication;
import junit.framework.TestCase;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;

import java.io.IOException;

/**
 * @author LarryKoo (gumutianqi@gmail.com)
 * @description TODO
 * @date 2017/10/24 09:32
 * @since 1.0.0
 */
public class ipTestCase extends TestCase {
    /**
     * IP 查询算法选择
     */
    private static final int algorithm = DbSearcher.MEMORY_ALGORITYM;

    private static final String dbFilePath = "data/ip2region.db";

    DbSearcher searcher;

    @Override
    public void setUp() throws Exception {
        String dbFile = HelloWorldApplication.class.getClassLoader().getResource(dbFilePath).getPath();
        DbConfig config = new DbConfig();
        searcher = new DbSearcher(config, dbFile);
    }

    public void testIP() throws IOException {
        DataBlock dataBlock = searcher.memorySearch("114.55.4.107");
        System.out.println(dataBlock);
        System.out.println(searcher.memorySearch("80.82.78.38").toString());
    }

}

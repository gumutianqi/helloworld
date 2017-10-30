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
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.engine.SQLParameter;
import org.beetl.sql.core.kit.EnumKit;
import org.beetl.sql.core.mapping.BeanProcessor;
import org.beetl.sql.core.mapping.type.JavaSqlTypeHandler;
import org.beetl.sql.core.mapping.type.TypeParameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author LarryKoo (gumutianqi@gmail.com)
 * @description LocalDateTime BeanProcessor
 * @date 2017/10/30 17:03
 * @since 1.0.0
 */
public class LocalDateTimeBeanProcessor extends BeanProcessor {
    public LocalDateTimeBeanProcessor(SQLManager sm) {
        super(sm);
        handlers.put(LocalDateTime.class, new JavaSqlTypeHandler() {
            @Override
            public Object getValue(TypeParameter typePara) throws SQLException {
                Timestamp ts = typePara.getRs().getTimestamp(typePara.getIndex());
                return ts == null ? null : ts.toLocalDateTime();
            }
        });
    }

    @Override
    public void setPreparedStatementPara(String sqlId, PreparedStatement ps, List<SQLParameter> objs)
            throws SQLException {
        for (int i = 0; i < objs.size(); i++) {
            SQLParameter para = objs.get(i);
            Object o = para.value;
            if (o == null) {
                ps.setObject(i + 1, o);
                continue;
            }
            Class c = o.getClass();
            if (c == LocalDateTime.class) {
                LocalDateTime d = (LocalDateTime) o;
                ps.setTimestamp(i + 1, Timestamp.valueOf(d));
                continue;
            }
            // 兼容性修改：oralce 驱动 不识别util.Date
            if (dbType == DBStyle.DB_ORACLE || dbType == DBStyle.DB_POSTGRES || dbType == DBStyle.DB_DB2) {
                if (c == java.util.Date.class) {
                    o = new Timestamp(((java.util.Date) o).getTime());
                }
            }
            if (Enum.class.isAssignableFrom(c)) {
                o = EnumKit.getValueByEnum(o);
            }
            //clob or text
            if (c == char[].class) {
                o = new String((char[]) o);
            }
            int jdbcType = para.getJdbcType();
            if (jdbcType == 0) {
                ps.setObject(i + 1, o);
            } else {
                //通常一些特殊的处理
                throw new UnsupportedOperationException(jdbcType + ",默认处理器并未处理此jdbc类型");
            }
        }
    }
}

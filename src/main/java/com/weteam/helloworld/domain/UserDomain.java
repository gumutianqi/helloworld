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
package com.weteam.helloworld.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author LarryKoo (gumutianqi@gmail.com)
 * @description User Domain
 * @date 2017/10/29 20:05
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserDomain implements Serializable {

    private static final long serialVersionUID = 4377659293391576296L;

    @AssignID("uuid")
    private String id;

    private String name;

    private Integer age;

    private String userName;

    private LocalDateTime createDate;

}

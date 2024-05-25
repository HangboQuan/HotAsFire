package com.alibaba.javabase.work.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author quanhangbo
 * @date 2024-05-25 14:45
 */
@Data
@Accessors(chain = true)
public class UserInfo {

    private Long userId;
    private String userName;
    private String nickName;
    private String avatar;
    private Integer birthday;
    private String address;

}

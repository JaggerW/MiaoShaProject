package com.nju.miaosha.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: jaggerw
 * @Description: user类
 * @Date: 2020/9/22
 */
@Data
public class UserDO implements Serializable {
    private static final long serialVersionUID = 8367165025568091928L;

    private Long userId;
    private String userName;
    private String password;
    private String userSalt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;

}

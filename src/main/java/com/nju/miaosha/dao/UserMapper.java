package com.nju.miaosha.dao;

import com.nju.miaosha.domain.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Author: jaggerw
 * @Description: TODO
 * @Date: 2020/9/22
 */
@Mapper
@Component
public interface UserMapper {

    @Select("select * from user where user_id = #{id}")
    UserDO getById(@Param("id") long id);

    @Insert("insert into user(user_id,user_name,password,user_salt,head,register_date,last_login_date,login_count)" +
            "values (#{userId},#{userName},#{password},#{userSalt},#{head},#{registerDate},#{lastLoginDate},#{loginCount})")
    Integer insert(UserDO userDO);
}

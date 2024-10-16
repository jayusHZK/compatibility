package com.jayus.smallMyBatis.step13.test.dao;

import com.jayus.smallMyBatis.step13.annotations.Delete;
import com.jayus.smallMyBatis.step13.annotations.Insert;
import com.jayus.smallMyBatis.step13.annotations.Select;
import com.jayus.smallMyBatis.step13.annotations.Update;
import com.jayus.smallMyBatis.step13.test.po.User;

import java.util.List;

/**
 * @ClassName IUserDao
 * @Description:
 * @date: 2024/9/18 21:14
 */
public interface IUserDao {

    @Select("SELECT id, user_name\n" +
            "        FROM sys_user\n" +
            "        where id = #{id}")
    User queryUserInfoById(Long id);

    @Select("SELECT id, user_name\n" +
            "        FROM sys_user\n" +
            "        where id = #{id}\n" +
            "        and user_name = #{user_name}")
    User queryUserInfo(User req);

    @Select("select id,user_name from sys_user;")
    List<User> queryUserInfoList();

    @Update("update sys_user set user_name = #{user_name} where id = #{id}")
    int update(User req);

    @Insert("insert into sys_user(user_name) values (#{user_name})")
    void insert(User req);

    @Delete("delete from sys_user where id = #{id}")
    int delete(Long userId);

}

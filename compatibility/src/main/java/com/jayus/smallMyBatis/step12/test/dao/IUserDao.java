package com.jayus.smallMyBatis.step12.test.dao;

import com.jayus.smallMyBatis.step12.test.po.User;

import java.util.List;

/**
 * @ClassName IUserDao
 * @Description:
 * @date: 2024/9/18 21:14
 */
public interface IUserDao {

    User queryUserInfoById(Long id);

    User queryUserInfo(User req);

    List<User> queryUserInfoList();

    int update(User req);

    void insert(User req);

    int delete(Long userId);

}

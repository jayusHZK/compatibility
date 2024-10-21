package com.jayus.smallMyBatis.step19.test.dao;

import com.jayus.smallMyBatis.step19.test.po.User;

/**
 * @ClassName UserDao
 * @Description:
 * @date: 2024/10/20 13:31
 */
public interface IUserDao {

    User queryById(User user);

}

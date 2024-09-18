package com.jayus.smallMyBatis.step11.test.dao;

import com.jayus.smallMyBatis.step11.test.po.User;

/**
 * @ClassName IUserDao
 * @Description:
 * @date: 2024/9/18 21:14
 */
public interface IUserDao {

    User queryUserInfoById(Long id);

    User queryUserInfo(User req);

}

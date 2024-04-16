package com.jayus.smallMyBatis.step10.test.dao;

import com.jayus.smallMyBatis.step10.test.po.User;

public interface IUserDao {

    User queryUserInfoById(Long id);

    User queryUserInfo(User req);

}

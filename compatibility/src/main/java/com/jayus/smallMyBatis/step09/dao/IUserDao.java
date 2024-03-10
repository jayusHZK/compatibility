package com.jayus.smallMyBatis.step09.dao;

import com.jayus.smallMyBatis.step09.po.User;

public interface IUserDao {

    User queryUserInfoById(Integer id);

    User queryUserInfo(User req);

}

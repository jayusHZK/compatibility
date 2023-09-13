package com.jayus.smallMyBatis.step04.dao;

import com.jayus.smallMyBatis.step04.po.User;

public interface IUserDao {

    User queryUserInfoById(Long uId);

}

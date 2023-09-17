package com.jayus.smallMyBatis.step07.dao;

import com.jayus.smallMyBatis.step07.po.User;

public interface IUserDao {

    User queryUserInfoById(Long uId);

}

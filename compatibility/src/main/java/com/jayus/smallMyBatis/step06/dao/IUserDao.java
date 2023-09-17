package com.jayus.smallMyBatis.step06.dao;

import com.jayus.smallMyBatis.step06.po.User;

public interface IUserDao {

    User queryUserInfoById(Integer uId);

}

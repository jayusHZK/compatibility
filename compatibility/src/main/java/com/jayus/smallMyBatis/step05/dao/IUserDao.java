package com.jayus.smallMyBatis.step05.dao;

import com.jayus.smallMyBatis.step05.po.User;

public interface IUserDao {

    User queryUserInfoById(Long uId);

}

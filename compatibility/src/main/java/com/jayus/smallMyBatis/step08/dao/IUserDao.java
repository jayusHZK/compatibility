package com.jayus.smallMyBatis.step08.dao;

import com.jayus.smallMyBatis.step08.po.User;

public interface IUserDao {
    User queryUserInfoById(Long uId);

    void sele();

}

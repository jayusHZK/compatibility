package com.jayus.smallMyBatis.step13.test.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName User
 * @Description:
 * @date: 2024/9/18 21:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public User(String user_name) {
        this.username = user_name;
    }

    private Long id;

    private String username;

}

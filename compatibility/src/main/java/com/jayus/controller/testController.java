package com.jayus.controller;

import com.jayus.vo.UserVO;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * @ClassName testController
 * @date 2022/5/20 22:02
 */
@RefreshScope
@RestController
//@RequestMapping("/test")
public class testController {

   // @Value("${api.client.key}")
    private String b;

    @RequestMapping("/a")
    public String getB(){
        return b;
    }

    @RequestMapping("b")
    public UserVO a(){
        UserVO userVO = new UserVO();
        userVO.setPassword(new Date().toString());
        userVO.setUsername("http://pic.netbian.com/4kdongman/");
        return userVO;
    }

    public static void main(String[] args) {
        int a = 1;
        while (a == 1){
            //System.out.println(1);
        }

    }


}

package com.jayus.controller;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.facefusion.v20181201.FacefusionClient;
import com.tencentcloudapi.facefusion.v20181201.models.CreateVideoMaterialRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName testController
 * @date 2022/5/20 22:02
 */
@RefreshScope
@RestController
public class testController {

    @Value("${api.client.key}")
    private String b;

    @RequestMapping("/a")
    public String getB(){
        return b;
    }

    public static void main(String[] args) {
        Credential cred = null;
        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("facefusion.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        // 实例化要请求产品的client对象,clientProfile是可选的
        FacefusionClient client = new FacefusionClient(cred, "", clientProfile);
        CreateVideoMaterialRequest request= new CreateVideoMaterialRequest();
        //request.setVideoUrl();
    }
}

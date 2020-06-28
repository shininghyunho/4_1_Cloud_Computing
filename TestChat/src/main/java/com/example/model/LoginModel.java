package com.example.model;

import com.example.controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public class LoginModel {
    final Logger log = LoggerFactory.getLogger(LoginModel.class);
    //LoggerModel loggerModel=new LoggerModel();

    //@LoginAnnotation(id="hyunho",channel = 1)
    public void login(){
        log.info("login successed. hyunho 1");
    }
    //@LoginAnnotation(id="hyunho",channel = 1)
    public void logout(){
        log.info("logout successed. hyunho 1");
    }

    public void login_attr(String id,int channel) throws Exception{
        log.info("login successed id:"+id+" channel:"+channel);
        // 수동 로그수집
        //loggerModel.saveLoginLog(id,channel);
    }
}

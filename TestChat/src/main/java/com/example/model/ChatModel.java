package com.example.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatModel {
    final Logger log = LoggerFactory.getLogger(LoginModel.class);
    public void chat(String id,int channel,String msg){
        // do something
        log.info("chat id:"+id+" channel:"+channel+" msg:\""+msg+"\"");
    }
}

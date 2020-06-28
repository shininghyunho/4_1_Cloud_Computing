package com.example.model;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

public class LoggerModel {
    @Async("threadPoolTaskExecutor")
    public Future<String> saveLoginLog(String id, int channel) throws Exception{
        // do something
        return new AsyncResult<String>("Successed");
    }
}

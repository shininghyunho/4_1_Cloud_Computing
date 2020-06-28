package com.example.model;

import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.Arrays;

public class RandomModel {
    Boolean[] isUser = new Boolean[10];
    Boolean[] isUser2 = new Boolean[10];
    ArrayList<String> users=new ArrayList<>();
    @Async("thread1")
    public void setUsers(){
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String user="user"+Integer.toString(i);
            users.add(user);
            isUser[i]=Boolean.TRUE;
            isUser2[i]=Boolean.TRUE;
        }
    }
    @Async("thread2")
    public void RandLogin(int channel) throws Exception {
        LoginModel loginModel=new LoginModel();
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int userNum=(int)(Math.random()*3);
            if(isUser2[userNum]==Boolean.TRUE){
                loginModel.login_attr(users.get(userNum),channel);
                isUser2[userNum]=Boolean.FALSE;
            }
        }
    }

    @Async("thread2")
    public void RandChat(int channel){
        ChatModel chatModel=new ChatModel();
        /*
        String[] msgList={"hello","who r you","my major is computer science",
        "cloud computing is fun","do you love weightlifting?","i luv squat",
        "shut up and squat","what kind of protein powder do you eat?","jaehoon is god",
        "uchan is powerful","kim jae won is my favorite bj","muscle is powerful than think"};
        */
        String[] msgList={"get out bodybuilding","i hate bodybuilding","Only Power","Make Power Greate Again"};
        for(int i=0;i<200;i++){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int userNum=(int)(Math.random()*10);
            if(isUser[userNum]==Boolean.TRUE){
                // 로그인 했으면 랜덤으로 채팅 ㄱㄱ
                int msgNum=(int)(Math.random()*msgList.length);
                chatModel.chat(users.get(userNum),channel,msgList[msgNum]);
            }
        }
    }


}

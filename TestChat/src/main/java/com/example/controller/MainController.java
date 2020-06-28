package com.example.controller;

import com.example.model.ChatModel;
import com.example.model.LoginModel;
import com.example.model.LoginAnnotation;
import com.example.model.RandomModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@RestController
public class MainController {
    //선언부
    final Logger log = LoggerFactory.getLogger(MainController.class);
    final LoginModel loginModel = new LoginModel();
    final ChatModel chatModel = new ChatModel();

    @RequestMapping("/test2")
    public String test2() throws NoSuchMethodException {

        //LoginModel loginModel=new LoginModel();
        //loginModel.login();
        //loginModel.logout();

        //사용시
        /*
        log.trace("This is TRACE Log!");
        log.debug("This is DEBUG Log!");
        log.info("This is INFO Log!");
        log.warn("This is WARN Log!");
        log.error("This is ERROR Log!");
        */

        /*
        Method method = LoginModel.class.getMethod("login");
        Annotation[] annotations=method.getDeclaredAnnotations();

        for(Annotation annotation : annotations){
            log.info(" annotation login "+annotation);
            if(annotation instanceof LoginAnnotation){
                LoginAnnotation loginAnnotation=(LoginAnnotation)annotation;
                log.info("login annotation");
                log.info("id "+loginAnnotation.id());
                log.info("channel "+loginAnnotation.channel());
            }
        }

        Annotation annotation=LoginModel.class.getMethod("logout").getAnnotation(LoginAnnotation.class);
        log.info(" annotation logout "+annotation);
        if(annotation instanceof LoginAnnotation){
            LoginAnnotation loginAnnotation=(LoginAnnotation) annotation;
            log.info("logout annotation");
            log.info("id "+loginAnnotation.id());
            log.info("channel "+loginAnnotation.channel());
        }
        */


        return "called test2";
    }

    @RequestMapping("/test")
    public String test(){
        loginModel.login();
        log.info("called login");

        return "이건 로그인 테스트";
    }
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/login_sirib")
    public String login_sirib() throws Exception {
        String id="sirib";
        int channel=1;
        loginModel.login_attr(id,channel);

        return "sirib 로그인 성공";
    }
    @RequestMapping("/login_uos")
    public String login_uos() throws Exception {
        String id="uos";
        int channel=1;
        loginModel.login_attr(id,channel);

        return "uos 로그인 성공";
    }
    @RequestMapping("/chat_sirib")
    public String chat_sirib() throws Exception {
        String id="sirib";
        int channel=1;
        String msg="hello";
        chatModel.chat(id,channel,msg);

        return "채팅중";
    }
    @RequestMapping("/rand_chat")
    public String rand_chat() throws Exception{
        for(int i=0;i<100;i++){
            RandomModel randomModel=new RandomModel();
            int channel=(int)(Math.random()*3);

            randomModel.setUsers();
            randomModel.RandLogin(channel);
            randomModel.RandChat(channel);
        }
        return "램덤 채팅중이에요 기다려요 좀";
    }

}

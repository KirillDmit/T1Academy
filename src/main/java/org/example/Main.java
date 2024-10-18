package org.example;

import org.example.config.AppConfig;
import org.example.service.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MyService myService = context.getBean(MyService.class);

        System.out.println(myService.someMethod());

        try {
            myService.throwingMethod();
        } catch (Exception e) {
        }
    }
}
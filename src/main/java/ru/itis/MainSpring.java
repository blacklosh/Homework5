package ru.itis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class MainSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        SignUpService signUpService = context.getBean(SignUpService.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println("type password2:");
        String password = scanner.next();
        System.out.println(signUpService.isBadPassword(password)?"Bad password2!":"Good password2!");
    }
}

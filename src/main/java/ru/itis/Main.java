package ru.itis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("C:\\Users\\Master3\\IdeaProjects\\05. Homework\\src\\main\\resources\\application.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        HikariConfig config = new HikariConfig();
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.user"));
        config.setPassword(properties.getProperty("db.password"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.pool-size")));

        DataSource dataSource = new HikariDataSource(config);

        PasswordRepository passwordRepository = new PasswordRepositoryJdbcTemplateImpl(dataSource);

        SignUpService signUpService = new SignUpService(passwordRepository);

        Scanner scanner = new Scanner(System.in);
        System.out.println("type password:");
        String password = scanner.next();
        System.out.println(signUpService.isBadPassword(password)?"Bad password!":"Good password!");
        //passwordRepository.savePasswordInBlacklist(password);
        //passwordRepository.deletePasswordFromBlacklist(password);
    }
}

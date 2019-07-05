package com.cms.scaffold.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailSenderConfig {

    @Value("${email.host}")
    private String host;
    @Value("${email.port}")
    private Integer port;
    @Value("${email.username}")
    private String username;
    @Value("${email.password}")
    private String password;

    @Bean
    public JavaMailSenderImpl mailSender(){
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setPort(port);
        sender.setProtocol("smtps");
        sender.setUsername(username);
        sender.setPassword(password);

        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtps.auth","true");
        javaMailProperties.setProperty("mail.smtp.ssl.enable","true");
        javaMailProperties.setProperty("mail.smtp.timeout","20000");
        javaMailProperties.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        sender.setJavaMailProperties(javaMailProperties);
        return sender;
    }
}

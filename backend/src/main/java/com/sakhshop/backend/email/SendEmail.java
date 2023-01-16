package com.sakhshop.backend.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {

    @Value("${sakhshop.backend.app.site.domain}")
    String siteDomain;

    private final
    JavaMailSender javaMailSender;


    @Value("${sakhshop.app.mail.email}")
    private String email;

    public SendEmail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public final void confirmEmailUser(String serverName, final String token){

        String path = siteDomain + "/registrationUser/confirmEmailUser/" + token;

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(email); // Кому
        msg.setFrom(email); // от кого

        msg.setSubject("Регистрация на сайте " + serverName);
        msg.setText("Для подтверждения электронно почты и активации аккаунта, вам необходимо пройти по ссылке " + path);

        javaMailSender.send(msg);
    }

    public final void confirmEmailSellerPerson(String serverName, final String token){

        String path = siteDomain + "/registrationSeller/confirmEmailSellerPerson/" + token;

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(email); // Кому
        msg.setFrom(email); // от кого

        msg.setSubject("Регистрация на сайте " + serverName + " в качестве продавца");
        msg.setText("Для подтверждения электронно почты и активации аккаунта продавца, вам необходимо пройти по ссылке " + path);

        javaMailSender.send(msg);
    }



    public final void resetPasswordUserAccount(String serverName, final String urlResetPasswordUserAccount){

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(email); // Кому
        msg.setFrom(email); // от кого

        msg.setSubject("Сброс пароля на сайте " + serverName);
        msg.setText("Для сброса пароля пройдите по ссылке "+ urlResetPasswordUserAccount);

        javaMailSender.send(msg);
    }


}

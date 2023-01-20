package com.sakhshop.backend.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {

    @Value("${sakhshop.app.main.site.domain.user}")
    String siteDomainUser;

    @Value("${sakhshop.app.main.site.domain.seller}")
    String siteDomainSeller;

    private final
    JavaMailSender javaMailSender;


    @Value("${sakhshop.app.mail.email}")
    private String email;

    public SendEmail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public final void confirmEmailUser(String serverName, final String token){

        String path = siteDomainUser + "/registration/confirmEmail/" + token;

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(email); // Кому
        msg.setFrom(email); // от кого

        msg.setSubject("Регистрация на сайте " + serverName);
        msg.setText("Для подтверждения электронно почты и активации аккаунта, вам необходимо пройти по ссылке " + path);

        javaMailSender.send(msg);
    }

    public final void confirmEmailSeller(String serverName, final String token){

        String path = siteDomainSeller + "/registration/confirmEmail/" + token;

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(email); // Кому
        msg.setFrom(email); // от кого

        msg.setSubject("Регистрация на сайте " + serverName + " в качестве продавца");
        msg.setText("Для подтверждения электронно почты и активации аккаунта продавца, вам необходимо пройти по ссылке " + path);

        javaMailSender.send(msg);
    }



    public final void resetPasswordUser(String serverName, final String urlResetPasswordUserAccount){

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(email); // Кому
        msg.setFrom(email); // от кого

        msg.setSubject("Сброс пароля пользователя на сайте " + serverName);
        msg.setText("Для сброса пароля пользователя пройдите по ссылке "+ urlResetPasswordUserAccount);

        javaMailSender.send(msg);
    }

    public final void resetPasswordSeller(String serverName, final String urlResetPasswordUserAccount){

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(email); // Кому
        msg.setFrom(email); // от кого

        msg.setSubject("Сброс пароля продавца на сайте " + serverName);
        msg.setText("Для сброса пароля продавца пройдите по ссылке "+ urlResetPasswordUserAccount);

        javaMailSender.send(msg);
    }


}

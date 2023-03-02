package com.projeto.notificacao.services;

import com.projeto.notificacao.entities.Email;
import com.projeto.notificacao.enums.StatusEmail;
import com.projeto.notificacao.repositories.EmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final EmailRepository emailRepository;

    public void sendEmail(Email email){
        email.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            javaMailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
            log.info(format("Status email: %s", email.getStatusEmail()));
        } catch (Exception ex){
            email.setStatusEmail(StatusEmail.ERROR);
            log.info(format("Status email: %s", email.getStatusEmail()));
        } finally {
            log.info(format("%s", email.getEmailTo()));
            emailRepository.save(email);
        }
    }
}

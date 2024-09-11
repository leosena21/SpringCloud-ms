package com.projeto.notificacao.consumers;

import com.projeto.notificacao.dto.EmailDTO;
import com.projeto.notificacao.entities.Email;
import com.projeto.notificacao.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.emailQueue}")
    public void listen(@Payload EmailDTO emailDTO){
        Email email = new Email();
        BeanUtils.copyProperties(emailDTO,email);
        emailService.sendEmail(email);
        log.info(format("Status email: %s", email.getStatusEmail()));
    }

}

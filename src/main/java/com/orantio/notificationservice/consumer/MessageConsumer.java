package com.orantio.notificationservice.consumer;

import com.orantio.notificationservice.Constant;
import com.orantio.notificationservice.dto.MessageDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    @RabbitListener(queues = Constant.MESSAGE_QUEUE)
    public void consumeMessage(MessageDTO message) {
        System.out.println("Received message: " + message);
    }
}

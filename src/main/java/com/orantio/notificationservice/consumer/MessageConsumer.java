package com.orantio.notificationservice.consumer;

import com.orantio.notificationservice.Constant;
import com.orantio.notificationservice.dto.DirectMessageDTO;
import com.orantio.notificationservice.dto.ServerMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConsumer {
    @RabbitListener(queues = Constant.MESSAGE_QUEUE)
    public void consumeDirectMessage(DirectMessageDTO message) {
        log.info("Received direct message: {}", message);
    }

    @RabbitListener(queues = Constant.MESSAGE_QUEUE)
    public void consumeServerMessage(ServerMessageDTO message) {
        log.info("Received server message: {}", message);
    }
}

package com.orantio.notificationservice.consumer;

import com.orantio.notificationservice.constant.Constant;
import com.orantio.notificationservice.model.TopicRequest;
import com.orantio.notificationservice.service.FirebaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TopicConsumer {
    private final FirebaseService firebaseService;

    @RabbitListener(queues = Constant.TOPIC_QUEUE)
    public void consumeTopicRequest(TopicRequest request) {
        try {
            switch (request.getType()) {
                case SUBSCRIBE:
                    firebaseService.subscribeTopic(request.getTopic(), request.getDeviceTokens());
                    break;
                case UNSUBSCRIBE:
                    firebaseService.unsubscribeTopic(request.getTopic(), request.getDeviceTokens());
                    break;
                default:
                    log.error("Invalid topic type: " + request.getType());
            }
        } catch (Exception e) {
            log.error("Error consuming topic request", e);
        }
    }
}

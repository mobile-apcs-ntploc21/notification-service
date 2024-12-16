package com.orantio.notificationservice.consumer;

import com.orantio.notificationservice.constant.Constant;
import com.orantio.notificationservice.model.NotificationRequest;
import com.orantio.notificationservice.service.FirebaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationConsumer {
    private final FirebaseService firebaseService;

    @RabbitListener(queues = Constant.NOTIFICATION_QUEUE)
    public void consumeNotificationRequest(NotificationRequest request) {
        try {
            firebaseService.sendNotification(request);
        } catch (Exception e) {
            log.error("Error consuming notification request", e);
        }
    }
}

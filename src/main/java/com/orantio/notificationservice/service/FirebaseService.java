package com.orantio.notificationservice.service;

import com.google.firebase.messaging.*;
import com.orantio.notificationservice.constant.NotificationParameter;
import com.orantio.notificationservice.model.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Slf4j
@Service
public class FirebaseService {
    public TopicManagementResponse subscribeTopic(String topic, List<String> deviceTokens) throws FirebaseMessagingException {
        TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(deviceTokens, topic);
        log.info("Topic " + topic + " has been subscribed to " + response.getSuccessCount() + " devices");
        return response;
    }

    public TopicManagementResponse unsubscribeTopic(String topic, List<String> deviceTokens) throws FirebaseMessagingException {
        TopicManagementResponse response = FirebaseMessaging.getInstance().unsubscribeFromTopic(deviceTokens, topic);
        log.info("Topic " + topic + " has been unsubscribed from " + response.getSuccessCount() + " devices");
        return response;
    }

    public String sendNotification(NotificationRequest request) throws FirebaseMessagingException {
        Message message = getPreconfiguredMessageBuilder(request).setTopic(request.getTopic()).build();
        String response = FirebaseMessaging.getInstance().send(message);
        log.info("Sent message to topic. Topic: " + request.getTopic() + ", " + response);
        return response;
    }

    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setSound(NotificationParameter.SOUND.getValue())
                        .setColor(NotificationParameter.COLOR.getValue()).setTag(topic).build()).build();
    }

    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(NotificationRequest request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        return Message.builder()
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
                        new Notification(request.getTitle(), request.getMessage()));
    }
}

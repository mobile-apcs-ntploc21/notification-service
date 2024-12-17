package com.orantio.notificationservice.service;

import com.google.api.core.ApiFuture;
import com.google.firebase.messaging.*;
import com.orantio.notificationservice.constant.NotificationParameter;
import com.orantio.notificationservice.model.Device;
import com.orantio.notificationservice.model.FirebaseTopic;
import com.orantio.notificationservice.model.NotificationRequest;
import com.orantio.notificationservice.repository.FirebaseTopicRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FirebaseService {
    private final FirebaseTopicRepo firebaseTopicRepo;
    private final MongoTemplate mongoTemplate;

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
        List<String> deviceTokens = getDeviceTokensByTopic(request.getTopic());
        if (deviceTokens.isEmpty()) {
            log.error("No device tokens found for topic " + request.getTopic());
            return "No device tokens found";
        } else {
            List<ApiFuture<String>> futures = new ArrayList<>();
            for (String deviceToken : deviceTokens) {
                Message message = getMessage(request).setToken(deviceToken).build();
                ApiFuture<String> future = FirebaseMessaging.getInstance().sendAsync(message);
                futures.add(future);
            }

            List<String> responses = new ArrayList<>();
            for (ApiFuture<String> future : futures) {
                try {
                    responses.add(future.get());
                } catch (Exception e) {
                    log.error("Failed to send message", e);
                }
            }

            log.info("Responses: {}", responses);
            return responses.toString();
        }
    }

    private List<String> getDeviceTokensByTopic(String topic) {
        // Step 1: Find user_ids for the given topic
        FirebaseTopic firebaseTopic = firebaseTopicRepo.findByTopic(topic);

        if (firebaseTopic == null || firebaseTopic.getUserIds().isEmpty()) {
            return new ArrayList<>(); // No users for this topic
        }

        // Step 2: Find device tokens for the user_ids
        Query deviceQuery = new Query(Criteria.where("user_id").in(firebaseTopic.getUserIds()));
        List<Device> devices = mongoTemplate.find(deviceQuery, Device.class, "devices");

        // Step 3: Collect all device tokens
        List<String> deviceTokens = new ArrayList<>();
        for (Device device : devices) {
            if (device.getDeviceTokens() != null) {
                deviceTokens.addAll(device.getDeviceTokens());
            }
        }

        return deviceTokens;
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

    private Message.Builder getMessage(NotificationRequest request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        return Message.builder()
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
                        new Notification(request.getTitle(), request.getMessage()));
    }

    private MulticastMessage.Builder getMulticastMessage(NotificationRequest request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        return MulticastMessage.builder()
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
                        new Notification(request.getTitle(), request.getMessage()));
    }
}

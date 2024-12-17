package com.orantio.notificationservice.repository;

import com.orantio.notificationservice.model.FirebaseTopic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FirebaseTopicRepo extends MongoRepository<FirebaseTopic, String> {
    FirebaseTopic findByTopic(String topic);
}

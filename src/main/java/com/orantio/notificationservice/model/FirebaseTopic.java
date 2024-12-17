package com.orantio.notificationservice.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "firebase_topics")
public class FirebaseTopic {
    @Field("topic")
    private String topic;

    @Field("user_ids")
    private List<ObjectId> userIds;
}

package com.orantio.notificationservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "devices")
public class Device {
    @Field("user_id")
    private String userId;

    @Field("device_tokens")
    private List<String> deviceTokens;
}

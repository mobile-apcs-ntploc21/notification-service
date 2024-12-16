package com.orantio.notificationservice.model;

import com.orantio.notificationservice.constant.TopicType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicRequest {
    private TopicType type;
    private String topic;
    private List<String> deviceTokens;
}

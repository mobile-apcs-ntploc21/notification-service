package com.orantio.notificationservice.constant;

import lombok.Getter;

@Getter
public enum TopicType {
    SUBSCRIBE("subscribe"),
    UNSUBSCRIBE("unsubscribe");

    private final String value;

    TopicType(String value) {
        this.value = value;
    }
}
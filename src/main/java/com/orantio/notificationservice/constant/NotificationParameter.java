package com.orantio.notificationservice.constant;

import lombok.Getter;

@Getter
public enum NotificationParameter {
    SOUND("default"),
    COLOR("#FFFF00");

    private String value;

    NotificationParameter(String value) {
        this.value = value;
    }
}

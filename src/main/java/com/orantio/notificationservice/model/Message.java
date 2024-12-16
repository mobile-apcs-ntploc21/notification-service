package com.orantio.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Message {
    private String senderName;
    private String message;
    private Date timestamp;
    private List<String> recipientTokens;
}

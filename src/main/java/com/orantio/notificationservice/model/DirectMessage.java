package com.orantio.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectMessage extends Message {
    private String senderAvatarURL;

    @Override
    public String toString() {
        return "DirectMessageDTO{" +
                "senderAvatarURL='" + senderAvatarURL + '\'' +
                ", senderName='" + getSenderName() + '\'' +
                ", message='" + getMessage() + '\'' +
                ", timestamp=" + getTimestamp() +
                ", recipientTokens=" + getRecipientTokens() +
                '}';
    }
}

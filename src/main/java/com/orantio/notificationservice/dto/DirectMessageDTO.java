package com.orantio.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectMessageDTO extends MessageDTO {
    private String senderName;
    private String senderAvatarURL;

    @Override
    public String toString() {
        return "DirectMessageDTO{" +
                "senderAvatarURL='" + senderAvatarURL + '\'' +
                ", senderName='" + senderName + '\'' +
                ", message='" + getMessage() + '\'' +
                ", timestamp=" + getTimestamp() +
                '}';
    }
}

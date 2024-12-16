package com.orantio.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerMessage extends Message {
    private String serverAvatarURL;
    private String serverName;
    private String channelName;

    @Override
    public String toString() {
        return "ServerMessageDTO{" +
                "serverAvatarURL='" + serverAvatarURL + '\'' +
                ", serverName='" + serverName + '\'' +
                ", channelName='" + channelName + '\'' +
                ", senderName='" + getSenderName() + '\'' +
                ", message='" + getMessage() + '\'' +
                ", timestamp=" + getTimestamp() +
                '}';
    }
}

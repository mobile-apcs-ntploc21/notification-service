package com.orantio.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerMessageDTO extends MessageDTO {
    private String serverAvatarURL;
    private String serverName;
    private String channelName;

    @Override
    public String toString() {
        return "ServerMessageDTO{" +
                "serverAvatarURL='" + serverAvatarURL + '\'' +
                ", serverName='" + serverName + '\'' +
                ", channelName='" + channelName + '\'' +
                ", message='" + getMessage() + '\'' +
                ", timestamp=" + getTimestamp() +
                '}';
    }
}

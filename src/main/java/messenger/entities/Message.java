package messenger.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Message implements Serializable {
    private UUID uuid;
    private User receiver;
    private User sender;
    private Date sendDate;
    private String body;
    private String type;

    public Message() {}

    public Message(User receiver, User sender, String body, String type) {
        this.uuid = UUID.randomUUID();
        this.receiver = receiver;
        this.sender = sender;
        this.sendDate = new Date();
        this.body = body;
        this.type = type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public User getReceiver() {
        return receiver;
    }

    public User getSender() {
        return sender;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public String getBody() {
        return body;
    }

    public String getType() {
        return type;
    }
}

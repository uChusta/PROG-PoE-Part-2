package cloudchat2.Logic;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {
    private String messageId;
    private int numMessagesSent;
    private String recipient;
    private String messageContent;
    private String messageHash;
    private long timestamp;

    // Constructor for creating a new message
    public Message(String messageId, int numMessagesSent, String recipient, String messageContent, String messageHash) {
        this.messageId = messageId;
        this.numMessagesSent = numMessagesSent;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageHash = messageHash;
        this.timestamp = System.currentTimeMillis();
    }

    // Constructor for loading from JSON
    public Message(JSONObject json) throws JSONException {
        this.messageId = json.getString("messageId");
        this.numMessagesSent = json.getInt("numMessagesSent");
        this.recipient = json.getString("recipient");
        this.messageContent = json.getString("messageContent");
        this.messageHash = json.getString("messageHash");
        this.timestamp = json.optLong("timestamp", System.currentTimeMillis());
    }

    // Getters
    public String getMessageId() {
        return messageId;
    }

    public int getNumMessagesSent() {
        return numMessagesSent;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Method to convert Message object to JSONObject for saving
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("messageId", messageId);
        json.put("numMessagesSent", numMessagesSent);
        json.put("recipient", recipient);
        json.put("messageContent", messageContent);
        json.put("messageHash", messageHash);
        json.put("timestamp", timestamp);
        return json;
    }
}
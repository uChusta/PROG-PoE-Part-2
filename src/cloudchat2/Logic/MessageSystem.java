package cloudchat2.Logic;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import org.json.JSONException;

public class MessageSystem {

    private List<Message> messages;
    private int messagesSentCount; // Tracks the number of messages sent or stored
    private String messageFileName;
    private final Random random = new Random();

    // Default constructor for main application use
    public MessageSystem() {
        this("messages.json"); // Uses default file name
    }

    // Constructor to specify the file name (useful for testing)
    public MessageSystem(String fileName) {
        this.messageFileName = fileName;
        messages = new ArrayList<>();
        messagesSentCount = 0;
        loadMessagesFromJson(); // Load existing messages on startup
    }

    private String generateUniqueMessageID() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString(); // Always generates a 10-digit number.
    }

    private boolean isValidRecipientPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+\\d{1,3}\\d{10}$");
    }

    private String generateMessageHash(String messageId, int currentMessageCount, String messageContent) {
        String idPrefix = messageId.substring(0, Math.min(messageId.length(), 2));
        String[] words = messageContent.trim().split("\\s+");
        String firstWord = "";
        String lastWord = "";

        if (words.length > 0) {
            firstWord = words[0];
            if (words.length > 1) {
                lastWord = words[words.length - 1];
            } else {
                lastWord = firstWord;
            }
        }

        return (idPrefix + ":" + currentMessageCount + ":" + firstWord + lastWord).toUpperCase();
    }

    public String processMessage(String recipient, String messageContent, String action) throws JSONException {
        if (!isValidRecipientPhoneNumber(recipient)) {
            return "Recipient cellphone number incorrectly formatted or does not contain international code.";
        }

        if (messageContent.length() > 250) {
            return "Message is too long. Please enter a message of less than 250 characters.";
        }

        if (action.equals("Send Message") && messageContent.length() > 50) {
            return "Please enter a message of less than 50 characters to send.";
        }

        String uniqueMessageId = generateUniqueMessageID();
        messagesSentCount++;
        String messageHash = generateMessageHash(uniqueMessageId, messagesSentCount, messageContent);

        Message newMessage = new Message(uniqueMessageId, messagesSentCount, recipient, messageContent, messageHash);

        switch (action) {
            case "Send Message":
                messages.add(newMessage);
                saveMessagesToJson();
                return "Message sent.";
            case "Store Message to send later":
                messages.add(newMessage);
                saveMessagesToJson();
                return "Message stored for later.";
            case "Disregard Message":
                messagesSentCount--; // Decrement if disregarded
                return "Message disregarded.";
            default:
                return "Invalid action.";
        }
    }

    private void saveMessagesToJson() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Message msg : messages) {
            jsonArray.put(msg.toJSONObject());
        }

        try (FileWriter file = new FileWriter(messageFileName)) {
            file.write(jsonArray.toString(4));
            file.flush();
        } catch (IOException e) {
            System.err.println("Error saving messages to JSON: " + e.getMessage());
        }
    }

    private void loadMessagesFromJson() {
        File file = new File(messageFileName);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            if (jsonContent.length() > 0) {
                JSONArray jsonArray = new JSONArray(jsonContent.toString());
                messages.clear();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    messages.add(new Message(obj));
                }
                if (!messages.isEmpty()) {
                    messagesSentCount = messages.get(messages.size() - 1).getNumMessagesSent();
                } else {
                    messagesSentCount = 0;
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading messages from JSON: " + e.getMessage());
        } catch (org.json.JSONException e) {
            System.err.println("Error parsing JSON data: " + e.getMessage());
        }
    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages);
    }

    public int getTotalMessagesSent() {
        return messagesSentCount;
    }

    public String printMessages() {
        StringBuilder sb = new StringBuilder();
        if (messages.isEmpty()) {
            sb.append("No messages sent or stored yet.");
        } else {
            sb.append("--- All Messages ---\n");
            for (Message msg : messages) {
                sb.append("Message ID: ").append(msg.getMessageId()).append("\n");
                sb.append("Message Hash: ").append(msg.getMessageHash()).append("\n");
                sb.append("Recipient: ").append(msg.getRecipient()).append("\n");
                sb.append("Message: ").append(msg.getMessageContent()).append("\n");
                sb.append("Num Sent (overall): ").append(msg.getNumMessagesSent()).append("\n");
                sb.append("Timestamp: ").append(new java.util.Date(msg.getTimestamp())).append("\n");
                sb.append("--------------------\n");
            }
            sb.append("Total messages processed (sent/stored): ").append(getTotalMessagesSent()).append("\n");
        }
        return sb.toString();
    }
}
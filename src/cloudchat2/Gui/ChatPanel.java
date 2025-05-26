package cloudchat2.Gui;

import cloudchat2.Logic.MessageSystem;
import cloudchat2.Logic.Message;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List; // Added import for List
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

public class ChatPanel extends javax.swing.JPanel {

    private Main main;
    private MessageSystem messageSystem;

    // Swing components
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField recipientTextField;
    private JLabel jLabel3;
    private JTextArea messageTextArea;
    private JScrollPane messageScrollPane;
    private JButton sendButton;
    private JButton disregardButton;
    private JButton storeButton;
    private JLabel messageIdLabel;
    private JLabel numMessagesSentLabel;
    private JLabel messageHashLabel;
    private JScrollPane chatLogScrollPane;
    private JTextArea chatLogTextArea;
    private JButton viewAllMessagesButton;

    public ChatPanel(Main main) {
        this.main = main;
        this.messageSystem = main.messageSystem;
        initComponents();
        updateMessageInfoLabels("N/A", "N/A", "N/A");
        updateTotalMessagesDisplay();
        updateChatLog();
    }

    private void initComponents() {
        this.setBackground(new java.awt.Color(0, 102, 102));
        this.setPreferredSize(new java.awt.Dimension(600, 600));

        jLabel1 = new JLabel("New Message");
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jLabel2 = new JLabel("Recipient (e.g., +271234567890)");
        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 16));
        recipientTextField = new JTextField();
        recipientTextField.setFont(new java.awt.Font("Segoe UI Black", 0, 14));

        jLabel3 = new JLabel("Message (max 250 chars for storage, 50 for sending)");
        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 16));
        messageTextArea = new JTextArea();
        messageTextArea.setFont(new java.awt.Font("Segoe UI Black", 0, 14));
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        messageScrollPane = new JScrollPane(messageTextArea);

        sendButton = new JButton("Send Message");
        sendButton.setFont(new java.awt.Font("Segoe UI Black", 1, 16));
        disregardButton = new JButton("Disregard Message");
        disregardButton.setFont(new java.awt.Font("Segoe UI Black", 1, 16));
        storeButton = new JButton("Store Message to send later");
        storeButton.setFont(new java.awt.Font("Segoe UI Black", 1, 16));
        viewAllMessagesButton = new JButton("View All Stored Messages");
        viewAllMessagesButton.setFont(new java.awt.Font("Segoe UI Black", 1, 14));

        messageIdLabel = new JLabel("Message ID: N/A");
        messageIdLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 14));
        numMessagesSentLabel = new JLabel("Total Messages Processed: N/A");
        numMessagesSentLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 14));
        messageHashLabel = new JLabel("Message Hash: N/A");
        messageHashLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 14));

        chatLogTextArea = new JTextArea();
        chatLogTextArea.setEditable(false);
        chatLogTextArea.setFont(new java.awt.Font("Segoe UI Black", 0, 12));
        chatLogTextArea.setLineWrap(true);
        chatLogTextArea.setWrapStyleWord(true);
        chatLogScrollPane = new JScrollPane(chatLogTextArea);
        chatLogScrollPane.setBorder(BorderFactory.createTitledBorder("Recent Messages Log"));


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(recipientTextField)
                    .addComponent(messageScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(disregardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(storeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(messageIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numMessagesSentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(messageHashLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chatLogScrollPane)
                    .addComponent(viewAllMessagesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recipientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendButton)
                    .addComponent(disregardButton)
                    .addComponent(storeButton))
                .addGap(18, 18, 18)
                .addComponent(messageIdLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numMessagesSentLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageHashLabel)
                .addGap(18, 18, 18)
                .addComponent(chatLogScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewAllMessagesButton)
                .addContainerGap())
        );

        sendButton.addActionListener(evt -> {
            try {
                processMessageAction("Send Message");
            } catch (JSONException ex) {
                Logger.getLogger(ChatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        disregardButton.addActionListener(evt -> {
            try {
                processMessageAction("Disregard Message");
            } catch (JSONException ex) {
                Logger.getLogger(ChatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        storeButton.addActionListener(evt -> {
            try {
                processMessageAction("Store Message to send later");
            } catch (JSONException ex) {
                Logger.getLogger(ChatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        viewAllMessagesButton.addActionListener(evt -> showAllStoredMessages());
    }

    private void processMessageAction(String action) throws JSONException {
        String recipient = recipientTextField.getText();
        String messageContent = messageTextArea.getText();

        String result = messageSystem.processMessage(recipient, messageContent, action);
        JOptionPane.showMessageDialog(this, result);

        if (result.equals("Message sent.") || result.equals("Message stored for later.")) {
            List<Message> allMessages = messageSystem.getAllMessages();
            if (!allMessages.isEmpty()) {
                Message lastMessage = allMessages.get(allMessages.size() - 1);
                String details = String.format(
                    "Message Details:\n" +
                    "Message ID: %s\n" +
                    "Message Hash: %s\n" +
                    "Recipient: %s\n" +
                    "Message: %s",
                    lastMessage.getMessageId(),
                    lastMessage.getMessageHash(),
                    lastMessage.getRecipient(),
                    lastMessage.getMessageContent()
                );
                JOptionPane.showMessageDialog(this, details, "Message Sent/Stored Details", JOptionPane.INFORMATION_MESSAGE);
            }

            recipientTextField.setText("");
            messageTextArea.setText("");
            updateChatLog();
            if (!allMessages.isEmpty()) {
                Message lastMessage = allMessages.get(allMessages.size() - 1);
                updateMessageInfoLabels(lastMessage.getMessageId(), String.valueOf(lastMessage.getNumMessagesSent()), lastMessage.getMessageHash());
            } else {
                updateMessageInfoLabels("N/A", "0", "N/A");
            }
            updateTotalMessagesDisplay();

        } else if (result.equals("Message disregarded.")) {
            recipientTextField.setText("");
            messageTextArea.setText("");
            updateMessageInfoLabels("N/A", String.valueOf(messageSystem.getTotalMessagesSent()), "N/A");
            updateTotalMessagesDisplay();
        } else {
            updateMessageInfoLabels("N/A", String.valueOf(messageSystem.getTotalMessagesSent()), "N/A");
            updateTotalMessagesDisplay();
        }
    }

    private void updateMessageInfoLabels(String id, String numProcessed, String hash) {
        messageIdLabel.setText("Message ID: " + id);
        messageHashLabel.setText("Message Hash: " + hash);
    }

    // New method to update the total messages display - ENSURED AT CLASS LEVEL
    private void updateTotalMessagesDisplay() {
        numMessagesSentLabel.setText("Total Messages Processed: " + messageSystem.getTotalMessagesSent());
    }

    private void updateChatLog() {
        StringBuilder log = new StringBuilder();
        if (messageSystem.getAllMessages().isEmpty()) {
            log.append("No messages sent or stored yet.");
        } else {
            for (Message msg : messageSystem.getAllMessages()) {
                log.append("ID: ").append(msg.getMessageId())
                   .append(" | Recipient: ").append(msg.getRecipient())
                   .append(" | Msg: ").append(msg.getMessageContent().substring(0, Math.min(msg.getMessageContent().length(), 40))).append("...")
                   .append(" | Hash: ").append(msg.getMessageHash())
                   .append(" (Overall #").append(msg.getNumMessagesSent()).append(")\n");
            }
        }
        chatLogTextArea.setText(log.toString());
        chatLogTextArea.setCaretPosition(chatLogTextArea.getDocument().getLength());
    }

    // Method for "View All Stored Messages" button - ENSURED AT CLASS LEVEL
    private void showAllStoredMessages() {
        String allMessagesText = messageSystem.printMessages();
        JOptionPane.showMessageDialog(this, allMessagesText, "All Stored/Sent Messages", JOptionPane.INFORMATION_MESSAGE);
    }
}
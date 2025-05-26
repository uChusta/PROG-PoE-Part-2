package cloudchat2.Logic;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.List;
import org.json.JSONException;

public class MessageSystemTest {

    private MessageSystem messageSystem;
    private static final String TEST_MESSAGE_FILE = "test_messages.json";

    @Before
    public void setUp() {
        // Delete the test file before each test to ensure a clean state
        File file = new File(TEST_MESSAGE_FILE);
        if (file.exists()) {
            file.delete();
        }
        messageSystem = new MessageSystem(TEST_MESSAGE_FILE);
    }

    @Test
    public void testMessageLengthSuccess() throws JSONException {
        String result = messageSystem.processMessage("+271234567890", "Short message.", "Send Message");
        assertEquals("Message sent.", result);
        assertEquals(1, messageSystem.getTotalMessagesSent());
    }

    @Test
    public void testMessageLengthFailure() throws JSONException {
        String longMessage = "This is a very long message that definitely exceeds fifty characters for sending " +
                             "and will also exceed two hundred and fifty characters for storage if it keeps going. " +
                             "Let's make sure it's super long, enough to cause an error. " +
                             "We need more text, much more text to hit the 250 char limit. " +
                             "This message is designed to be excessively long to test the message length validation. " +
                             "It needs to be over 250 characters to trigger the specific failure condition for storage."; // Length ~400
        int excessChars = longMessage.length() - 250;
        String expectedErrorMessage = "Message is too long. Please enter a message of less than 250 characters."; 
        String result = messageSystem.processMessage("+271234567890", longMessage, "Store Message to send later");
        assertEquals(expectedErrorMessage, result);
        assertEquals(0, messageSystem.getTotalMessagesSent());

        // Test send message length failure (over 50 chars)
        String longSendMessage = "This message is longer than fifty characters for sending."; // Length > 50
        result = messageSystem.processMessage("+271234567890", longSendMessage, "Send Message");
        assertEquals("Please enter a message of less than 50 characters to send.", result);
        assertEquals(0, messageSystem.getTotalMessagesSent());
    }

    @Test
    public void testRecipientNumberCorrectlyFormatted() throws JSONException {
        // "Cell phone number successfully captured."
        String result = messageSystem.processMessage("+27718693002", "Hi.", "Send Message");
        assertEquals("Message sent.", result);
        assertEquals(1, messageSystem.getTotalMessagesSent());
    }

    @Test
    public void testRecipientNumberIncorrectlyFormatted() throws JSONException {
        String result = messageSystem.processMessage("08575975889", "Hi.", "Send Message");
        assertEquals("Recipient cellphone number incorrectly formatted or does not contain international code.", result);
        assertEquals(0, messageSystem.getTotalMessagesSent());
    }

    @Test
    public void testMessageHashIsCorrect() throws JSONException {
        String recipient = "+27718693002";
        String messageContent = "Hi Mike, can you join us for dinner tonight";
        messageSystem.processMessage(recipient, messageContent, "Send Message");
        List<Message> sentMessages = messageSystem.getAllMessages();
        assertFalse(sentMessages.isEmpty());
        Message sentMessage = sentMessages.get(0);

        String messageIdPrefix = sentMessage.getMessageId().substring(0, 2); // Get the generated prefix
        String expectedHash = (messageIdPrefix + ":" + sentMessage.getNumMessagesSent() + ":" + "Hitonight").toUpperCase();
        assertEquals(expectedHash, sentMessage.getMessageHash());

    }

    @Test
    public void testMessageIDIsCreated() throws JSONException {
        // "Message ID generated: <Message ID>" [cite: 2]
        String recipient = "+271234567890";
        String messageContent = "Test message.";
        messageSystem.processMessage(recipient, messageContent, "Send Message");
        List<Message> sentMessages = messageSystem.getAllMessages();
        assertFalse(sentMessages.isEmpty());
        Message sentMessage = sentMessages.get(0);

        assertNotNull(sentMessage.getMessageId());
        assertEquals(10, sentMessage.getMessageId().length());
        assertTrue(sentMessage.getMessageId().matches("\\d{10}"));
    }

    @Test
    public void testMessageSentAction() throws JSONException {
        // "User selected ‘Send Message’", "Message successfully sent."
        String recipient = "+271234567890";
        String messageContent = "This is a short message.";
        String result = messageSystem.processMessage(recipient, messageContent, "Send Message");
        assertEquals("Message sent.", result);
        assertEquals(1, messageSystem.getTotalMessagesSent());
        assertEquals(1, messageSystem.getAllMessages().size());
        assertEquals(messageContent, messageSystem.getAllMessages().get(0).getMessageContent());
    }

    @Test
    public void testMessageDisregardAction() throws JSONException {
        String recipient = "08575975889";
        String messageContent = "Hi Keegan, did you receive the payment?";
        String result = messageSystem.processMessage(recipient, messageContent, "Disregard Message");
        assertEquals("Message disregarded.", result);
        assertEquals(0, messageSystem.getTotalMessagesSent()); // Should not count disregarded messages
        assertTrue(messageSystem.getAllMessages().isEmpty());
    }

    @Test
    public void testMessageStoreAction() throws JSONException {
        // "User selected ‘Store Message’", "Message successfully stored."
        String recipient = "+271234567890";
        String messageContent = "This message is for later, it can be longer than 50 characters but less than 250 characters.";
        String result = messageSystem.processMessage(recipient, messageContent, "Store Message to send later");
        assertEquals("Message stored for later.", result);
        assertEquals(1, messageSystem.getTotalMessagesSent());
        assertEquals(1, messageSystem.getAllMessages().size());
        assertEquals(messageContent, messageSystem.getAllMessages().get(0).getMessageContent());
    }

    @Test
    public void testTotalMessagesCount() throws JSONException {
        assertEquals(0, messageSystem.getTotalMessagesSent());
        messageSystem.processMessage("+271111111111", "Msg 1", "Send Message");
        assertEquals(1, messageSystem.getTotalMessagesSent());
        messageSystem.processMessage("+272222222222", "Msg 2", "Store Message to send later");
        assertEquals(2, messageSystem.getTotalMessagesSent());
        messageSystem.processMessage("+273333333333", "Msg 3", "Disregard Message");
        assertEquals(2, messageSystem.getTotalMessagesSent()); 
        messageSystem.processMessage("invalid", "Msg 4", "Send Message");
        assertEquals(2, messageSystem.getTotalMessagesSent()); 
    }

    @Test
    public void testPrintMessages() throws JSONException {
        String recipient1 = "+271111111111";
        String messageContent1 = "Test Message One";
        messageSystem.processMessage(recipient1, messageContent1, "Send Message");

        String recipient2 = "+272222222222";
        String messageContent2 = "Test Message Two for storage";
        messageSystem.processMessage(recipient2, messageContent2, "Store Message to send later");

        String printedMessages = messageSystem.printMessages();
        System.out.println(printedMessages);

        assertTrue(printedMessages.contains("--- All Messages ---"));
        assertTrue(printedMessages.contains("Recipient: " + recipient1));
        assertTrue(printedMessages.contains("Message: " + messageContent1));
        assertTrue(printedMessages.contains("Recipient: " + recipient2));
        assertTrue(printedMessages.contains("Message: " + messageContent2));
        assertTrue(printedMessages.contains("Total messages processed (sent/stored): 2"));
    }
}
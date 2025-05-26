package cloudchat2.Logic;

import java.io.BufferedWriter;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;

public class LoginTest {

    private Login login;
    private static final String TEST_FILE_NAME = "test_users.txt";

    /**
     *
     * @throws IOException
     */
    @Before
    public void setUp() throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter("test_users.txt"));
    writer.write("testuser,password123,1234567890\n");
    writer.close();
    login = new Login("test_users.txt");
    }
    
    @After
    public void tearDown() {
    new File("test_users.txt").delete();
}


    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordFailsComplexity() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellPhoneCorrect() {
        assertTrue(login.checkCellPhoneNumber("+271234567890"));
    }

    @Test
    public void testCellPhoneIncorrect() {
        assertFalse(login.checkCellPhoneNumber("012345"));
    }

    @Test
    public void testRegisterUserSuccess() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+271234567890", "Kyle", "Smith");
        assertEquals("User has been registered successfully.", result);
    }

    @Test
    public void testRegisterUserFailUsername() {
        String result = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+271234567890", "Kyle", "Smith");
        assertEquals("Username is not correctly formatted", result);
    }

    @Test
    public void testRegisterUserExistingUsername() {
        // Use a valid username that meets the checkUserName criteria (e.g., "user_1")
        login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+271234567890", "Kyle", "Smith"); // First registration
        String result; // Second registration with same username
        result = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+271234567890", "Kyle", "Smith");
        assertEquals("Username already exists.", result);
    }

    @Test
    public void testRegisterUserFailPassword() {
        String result = login.registerUser("kyl_1", "password", "+271234567890", "Kyle", "Smith");
        assertEquals("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.", result);
    }

    @Test
    public void testLoginSuccess() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+271234567890", "Kyle", "Smith");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFail() {
        assertFalse(login.loginUser("wrong", "wrongpass"));
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+271234567890", "Kyle", "Smith");
        login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Kyle, Smith it is great to see you again.", login.returnLoginStatus());
    }

    @Test
    public void testReturnLoginStatusFail() {
        login.loginUser("invalid", "invalid");
        assertEquals("Username or password incorrect, please try again", login.returnLoginStatus());
    }
}
//*OpenAI. (2023). ChatGPT (Mar 14 version) [Large language model]. https://chat.openai.com/chat 
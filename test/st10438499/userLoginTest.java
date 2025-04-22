package st10438499;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author noxid
 */
public class userLoginTest {
    
    userLogin instance;

    public userLoginTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println("Starting userLogin tests...");
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("All tests complete.");
    }
    
    @BeforeEach
    public void setUp() {
        instance = new userLogin();
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of Username method - Valid Case
     */
    @Test
    public void testValidUsername() {
        System.out.println("Valid Username Test");
        String username = "us_r"; // valid: contains "_" and <= 5 chars
        Boolean result = instance.Username(username);
        assertTrue(result);
    }

    /**
     * Test of Username method - Invalid Case
     */
    @Test
    public void testInvalidUsername() {
        System.out.println("Invalid Username Test");
        String username = "userlong"; // invalid: too long and no "_"
        Boolean result = instance.Username(username);
        assertFalse(result);
    }

    /**
     * Test of Password method - Valid Case
     */
    @Test
    public void testValidPassword() {
        System.out.println("Valid Password Test");
        String password = "P@ssw0rd"; // valid
        Boolean result = instance.Password(password);
        assertTrue(result);
    }

    /**
     * Test of Password method - Missing Special Char
     */
    @Test
    public void testPasswordMissingSpecialChar() {
        System.out.println("Password Missing Special Char Test");
        String password = "Passw0rd"; // no special character
        Boolean result = instance.Password(password);
        assertFalse(result);
    }

    /**
     * Test of Password method - Missing Number
     */
    @Test
    public void testPasswordMissingNumber() {
        System.out.println("Password Missing Number Test");
        String password = "P@ssword"; // no number
        Boolean result = instance.Password(password);
        assertFalse(result);
    }

    /**
     * Test of Password method - Missing Capital Letter
     */
    @Test
    public void testPasswordMissingCapital() {
        System.out.println("Password Missing Capital Letter Test");
        String password = "p@ssw0rd"; // no capital
        Boolean result = instance.Password(password);
        assertFalse(result);
    }

    /**
     * Test of Password method - Contains Space
     */
    @Test
    public void testPasswordWithSpace() {
        System.out.println("Password Contains Space Test");
        String password = "P@ss w0rd"; // contains space
        Boolean result = instance.Password(password);
        assertFalse(result);
    }

    /**
     * Test of CellPhoneValidator - Valid
     */
    @Test
    public void testValidCellPhone() {
        System.out.println("Valid Cell Phone Test");
        String cell = "+27831234567"; // valid
        assertTrue(CellPhoneValidator.validateCellPhone(cell));
    }

    /**
     * Test of CellPhoneValidator - Invalid (no +27)
     */
    @Test
    public void testCellPhoneMissingCode() {
        System.out.println("Invalid Cell Phone Test - Missing Code");
        String cell = "0831234567"; // no +27
        assertFalse(CellPhoneValidator.validateCellPhone(cell));
    }

    /**
     * Test of CellPhoneValidator - Invalid (too short)
     */
    @Test
    public void testCellPhoneTooShort() {
        System.out.println("Invalid Cell Phone Test - Too Short");
        String cell = "+27831234"; // too short
        assertFalse(CellPhoneValidator.validateCellPhone(cell));
    }

    /**
     * Skipping testMain - not suitable for unit testing due to scanner input.
     */
    @Test
    public void skipTestMain() {
        System.out.println("Skipping main method test as it requires console input.");
        assertTrue(true); // placeholder to skip fail
    }
}

package ticket.booking;

import org.junit.jupiter.api.Test;
import ticket.booking.util.UserServiceUtil;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceUtilTest {

    @Test
    void passwordHashingShouldWork() {
        String password = "admin123";

        String hashedPassword = UserServiceUtil.hashPassword(password);

        assertTrue(
                UserServiceUtil.checkPassword(password, hashedPassword)
        );
    }
}
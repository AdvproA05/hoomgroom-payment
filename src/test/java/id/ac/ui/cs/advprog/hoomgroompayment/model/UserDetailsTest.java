package id.ac.ui.cs.advprog.hoomgroompayment.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsTest {
    private List<UserDetails> userDetails;

    @BeforeEach
    void setup() {
        this.userDetails = new ArrayList<>();
        UserDetails user1 = new UserDetails();
        user1.setId(20240501164729929L);
        user1.setUsername("Virgillia Yeala Prabowo");
        user1.setWalletBalance(200.0);

        UserDetails user2 = new UserDetails();
        user2.setId(20240501164730701L);
        user2.setUsername("Valerian Yoel Prabowo");
        user2.setWalletBalance(200.0);

        this.userDetails.add(user1);
        this.userDetails.add(user2);
    }

    @Test
    public void testSetUsername() {
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername("john_doe");

        assertEquals("john_doe", userDetails.getUsername());
    }

    @Test
    public void testSetWalletBalance() {
        UserDetails userDetails = new UserDetails();
        userDetails.setWalletBalance(100.0);

        assertEquals(100.0, userDetails.getWalletBalance());
    }

    @Test
    public void testSetId() {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(20240601164729929L);

        assertEquals(20240601164729929L, userDetails.getId());
    }

    @Test
    public void testCreateUserDetailsSuccess() {
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername("john_doe");
        userDetails.setWalletBalance(100.0);
        userDetails.setId(20240601164729929L);

        assertEquals("john_doe", userDetails.getUsername());
        assertEquals(100.0, userDetails.getWalletBalance());
        assertEquals(20240601164729929L, userDetails.getId());
    }

    @Test
    public void testCreateUserDetailsFailure() {
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername("john_doe");
        userDetails.setWalletBalance(100.0);
        userDetails.setId(20240601164729929L);

        assertNotEquals("jane_doe", userDetails.getUsername());
        assertNotEquals(50.0, userDetails.getWalletBalance());
        assertNotEquals(20240501164730701L, userDetails.getId());
    }

    @Test
    public void testUserEquality() {
        UserDetails user1 = userDetails.get(0);
        UserDetails user2 = userDetails.get(1);

        assertNotEquals(user1, user2);
    }
}
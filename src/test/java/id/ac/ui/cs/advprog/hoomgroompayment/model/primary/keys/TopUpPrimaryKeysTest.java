package id.ac.ui.cs.advprog.hoomgroompayment.model.primary.keys;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TopUpPrimaryKeysTest {

    @DisplayName("Should return true when comparing two equal TopUpPrimaryKeys")
    @Test
    public void shouldReturnTrueWhenComparingTwoEqualTopUpPrimaryKeys() {
        TopUpPrimaryKeys keys1 = new TopUpPrimaryKeys();
        keys1.setTimestamp(new Date());
        keys1.setUsername("testUser");

        TopUpPrimaryKeys keys2 = new TopUpPrimaryKeys();
        keys2.setTimestamp(keys1.getTimestamp());
        keys2.setUsername("testUser");

        assertTrue(keys1.equals(keys2));
    }

    @DisplayName("Should return false when comparing two different TopUpPrimaryKeys")
    @Test
    public void shouldReturnFalseWhenComparingTwoDifferentTopUpPrimaryKeys() {
        TopUpPrimaryKeys keys1 = new TopUpPrimaryKeys();
        keys1.setTimestamp(new Date());
        keys1.setUsername("testUser");

        TopUpPrimaryKeys keys2 = new TopUpPrimaryKeys();
        keys2.setTimestamp(new Date());
        keys2.setUsername("anotherUser");

        assertFalse(keys1.equals(keys2));
    }

    @DisplayName("Should return same hashcode for two equal TopUpPrimaryKeys")
    @Test
    public void shouldReturnSameHashcodeForTwoEqualTopUpPrimaryKeys() {
        TopUpPrimaryKeys keys1 = new TopUpPrimaryKeys();
        keys1.setTimestamp(new Date());
        keys1.setUsername("testUser");

        TopUpPrimaryKeys keys2 = new TopUpPrimaryKeys();
        keys2.setTimestamp(keys1.getTimestamp());
        keys2.setUsername("testUser");

        assertEquals(keys1.hashCode(), keys2.hashCode());
    }

    @DisplayName("Should return different hashcodes for two different TopUpPrimaryKeys")
    @Test
    public void shouldReturnDifferentHashcodesForTwoDifferentTopUpPrimaryKeys() {
        TopUpPrimaryKeys keys1 = new TopUpPrimaryKeys();
        keys1.setTimestamp(new Date());
        keys1.setUsername("testUser");

        TopUpPrimaryKeys keys2 = new TopUpPrimaryKeys();
        keys2.setTimestamp(new Date());
        keys2.setUsername("anotherUser");

        assertNotEquals(keys1.hashCode(), keys2.hashCode());
    }
}
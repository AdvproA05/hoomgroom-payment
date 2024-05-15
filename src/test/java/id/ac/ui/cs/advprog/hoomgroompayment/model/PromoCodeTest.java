package id.ac.ui.cs.advprog.hoomgroompayment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

public class PromoCodeTest {
    
    PromoCode promoCode;

    @BeforeEach
    void setUp() throws Exception {
        promoCode = new PromoCode.Builder()
                .withId(UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194"))
                .withName("PAYDAY50")
                .withDescription("Berlaku tanggal 1-5 setiap bulannya")
                .withValidDate(LocalDate.of(2024, 12, 31))
                .withMinPurchase(50000.0)
                .build();
    }

    @Test
    void testGetId() {
        UUID id = UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194");
        assertEquals(id, promoCode.getId());
    }

    @Test
    void testGetName() {
        assertEquals("PAYDAY50", promoCode.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals("Berlaku tanggal 1-5 setiap bulannya", promoCode.getDescription());
    }

    @Test
    void testGetValidDate() {
        assertEquals(LocalDate.of(2024, 12, 31), promoCode.getValidDate());
    }

    @Test
    void testGetMinPurchase() {
        assertEquals(Double.valueOf(50000), promoCode.getMinPurchase());
    }
}

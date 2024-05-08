package id.ac.ui.cs.advprog.hoomgroompayment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import id.ac.ui.cs.advprog.hoomgroompayment.repository.PromoCodeRepository;
import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;

@ExtendWith(MockitoExtension.class)
public class PromoCodeServiceImplTest {
    @InjectMocks
    PromoCodeServiceImpl promoCodeService;
    @Mock
    PromoCodeRepository promoCodeRepository;
    List<PromoCode> promoCodes = new ArrayList<PromoCode>();

    @Test
    void testCreatePromoCodeValid() {
        PromoCode promoCode = new PromoCode.Builder()
                .withId(UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194"))
                .withName("PAYDAY50")
                .withDescription("Berlaku tanggal 1-5 setiap bulannya")
                .withValidDate(LocalDate.of(2024, 12, 31))
                .withMinPurchase(50000.0)
                .build();

        when(promoCodeRepository.save(any())).thenReturn(promoCode);

        PromoCode createdPromoCode = promoCodeService.createPromoCode(promoCode);

        assertNotNull(createdPromoCode);
        verify(promoCodeRepository, times(1)).save(any());
    }

    @Test
    void testCreatePromoCodeInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            PromoCode promoCode = new PromoCode.Builder()
                    .withId(UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194"))
                    .withName("#####") // Menggunakan nama yang valid
                    .withDescription("Berlaku tanggal 1-5 setiap bulannya")
                    .withValidDate(LocalDate.of(2024, 12, 31))
                    .withMinPurchase(50000.0)
                    .build();

            promoCodeService.createPromoCode(promoCode);
        });
        verify(promoCodeRepository, times(0)).save(any());
    }

    @Test
    void testFindPromoCode() {
        PromoCode promoCode = new PromoCode.Builder()
                .withId(UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194"))
                .withName("PAYDAY50")
                .withDescription("Berlaku tanggal 1-5 setiap bulannya")
                .withValidDate(LocalDate.of(2024, 12, 31))
                .withMinPurchase(50000.0)
                .build();

        when(promoCodeRepository.findById(any(UUID.class))).thenReturn(Optional.of(promoCode));

        PromoCode retrievedPromoCode = promoCodeService.findById(promoCode.getId());

        assertNotNull(retrievedPromoCode);
        assertEquals(promoCode.getId(), retrievedPromoCode.getId());
    }

    @Test
    void testFindPromoCodeNotExist() {
        UUID nonExistingId = UUID.randomUUID();
        when(promoCodeRepository.findById(any(UUID.class))).thenReturn(null);

        PromoCode retrievedPromoCode = promoCodeService.findById(nonExistingId);

        assertNull(retrievedPromoCode);
    }

    @Test
    void testUpdatePromoCode() {
        PromoCode promoCode = new PromoCode.Builder()
                .withId(UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194"))
                .withName("PAYDAY50")
                .withDescription("Berlaku tanggal 1-5 setiap bulannya")
                .withValidDate(LocalDate.of(2024, 12, 31))
                .withMinPurchase(50000.0)
                .build();

        when(promoCodeRepository.findById(any(UUID.class))).thenReturn(Optional.of(promoCode));
        when(promoCodeRepository.save(any(PromoCode.class))).thenReturn(promoCode);

        PromoCode updatedPromoCode = promoCodeService.updatePromoCode(promoCode.getId(), promoCode);

        assertNotNull(updatedPromoCode);
        assertEquals(promoCode.getId(), updatedPromoCode.getId());
    }

    @Test
    void testUpdatePromoCodeNotExist() {
        UUID nonExistingId = UUID.randomUUID();
        PromoCode promoCode = new PromoCode.Builder()
                .withId(nonExistingId)
                .withName("PAYDAY50")
                .withDescription("Berlaku tanggal 1-5 setiap bulannya")
                .withValidDate(LocalDate.of(2024, 12, 31))
                .withMinPurchase(50000.0)
                .build();

        when(promoCodeRepository.findById(any(UUID.class))).thenReturn(null);
        
        PromoCode updatedPromoCode = promoCodeService.updatePromoCode(nonExistingId, promoCode);

        assertNull(updatedPromoCode);
    }

    @Test
    void testDeletePromoCode() {
        PromoCode promoCode = new PromoCode.Builder()
                .withId(UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194"))
                .withName("PAYDAY50")
                .withDescription("Berlaku tanggal 1-5 setiap bulannya")
                .withValidDate(LocalDate.of(2024, 12, 31))
                .withMinPurchase(50000.0)
                .build();

        promoCodeService.deletePromoCode(promoCode.getId());
        verify(promoCodeRepository, times(1)).deleteById(promoCode.getId());
    }
}

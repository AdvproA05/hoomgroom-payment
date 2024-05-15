package id.ac.ui.cs.advprog.hoomgroompayment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import id.ac.ui.cs.advprog.hoomgroompayment.repository.PromoCodeRepository;
import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;

@ExtendWith(MockitoExtension.class)
public class PromoCodeServiceImplTest {
    @InjectMocks
    PromoCodeServiceImpl promoCodeService;
    @Mock
    PromoCodeRepository promoCodeRepository;

    @Test
    void testCreatePromoCodeValid() {
        PromoCode promoCode = new PromoCode.Builder()
                .withId(UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194"))
                .withName("PAYDAY50") // Menggunakan nama yang valid
                .withDescription("Berlaku tanggal 1-5 setiap bulannya")
                .withValidDate(LocalDate.of(2024, 12, 31))
                .withMinPurchase(50000.0)
                .build();

        when(promoCodeRepository.save(promoCode)).thenReturn(promoCode);

        CompletableFuture<PromoCode> future = promoCodeService.createPromoCode(promoCode);
        PromoCode result = future.join();

        assertNotNull(result);
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

            CompletableFuture<PromoCode> future = CompletableFuture.completedFuture(promoCode);
            promoCodeService.createPromoCode(promoCode);
        });
        verify(promoCodeRepository, times(0)).save(any());
    }

    @Test
    void testFindPromoCode() {
        PromoCode promoCode = new PromoCode.Builder()
                .withId(UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194"))
                .withName("PAYDAY50") // Menggunakan nama yang valid
                .withDescription("Berlaku tanggal 1-5 setiap bulannya")
                .withValidDate(LocalDate.of(2024, 12, 31))
                .withMinPurchase(50000.0)
                .build();

        when(promoCodeRepository.findById(promoCode.getId())).thenReturn(Optional.of(promoCode));

        CompletableFuture<PromoCode> future = promoCodeService.findById(promoCode.getId());
        PromoCode result = future.join();

        assertNotNull(result);
        assertEquals(promoCode.getId(), result.getId());
    }

    @Test
    void testFindPromoCodeNotExist() {
        CompletableFuture<PromoCode> retrievedPromoCodeFuture = promoCodeService.findById(UUID.randomUUID());

        PromoCode retrievedPromoCode = retrievedPromoCodeFuture.join();
        assertNull(retrievedPromoCode);
    }

    @Test
    void testUpdatePromoCode() {
        PromoCode promoCode = new PromoCode.Builder()
                .withId(UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194"))
                .withName("PAYDAY50") // Menggunakan nama yang valid
                .withDescription("Berlaku tanggal 1-5 setiap bulannya")
                .withValidDate(LocalDate.of(2024, 12, 31))
                .withMinPurchase(50000.0)
                .build();

        when(promoCodeRepository.findById(promoCode.getId())).thenReturn(Optional.of(promoCode));
        when(promoCodeRepository.save(promoCode)).thenReturn(promoCode);

        CompletableFuture<PromoCode> future = promoCodeService.updatePromoCode(promoCode.getId(), promoCode);
        PromoCode result = future.join();

        assertNotNull(result);
        assertEquals(promoCode.getId(), result.getId());
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

        CompletableFuture<PromoCode> updatedPromoCode = promoCodeService.updatePromoCode(nonExistingId, promoCode);

        assertNull(updatedPromoCode.join());
    }

    @Test
    void testDeletePromoCode() {
        UUID id = UUID.randomUUID();

        CompletableFuture<Void> future = promoCodeService.deletePromoCode(id);
        future.join();

        verify(promoCodeRepository, times(1)).deleteById(id);
    }

}

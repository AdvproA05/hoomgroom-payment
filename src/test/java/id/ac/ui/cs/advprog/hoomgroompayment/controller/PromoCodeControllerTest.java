package id.ac.ui.cs.advprog.hoomgroompayment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;
import id.ac.ui.cs.advprog.hoomgroompayment.service.PromoCodeService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PromoCodeControllerTest {
    
    @Mock
    PromoCodeService promoCodeService;

    @InjectMocks
    PromoCodeController promoCodeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreatePromoCodePost() throws Exception {
        PromoCode promoCode = new PromoCode.Builder()
                .withId(UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194"))
                .withName("PAYDAY50")
                .withDescription("Berlaku tanggal 1-5 setiap bulannya")
                .withValidDate(LocalDate.of(2024, 12, 31))
                .withMinPurchase(50000.0)
                .build();

        ResponseEntity<PromoCode> response = promoCodeController.createPromoCodePost(promoCode);

        verify(promoCodeService, times(1)).createPromoCode(promoCode);
        assert(response.getStatusCode() == HttpStatus.CREATED);
    }

    @Test
    void testReadPromoCodePage() throws Exception {
        PromoCode promoCode = new PromoCode.Builder()
                .withId(UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194"))
                .withName("PAYDAY50")
                .withDescription("Berlaku tanggal 1-5 setiap bulannya")
                .withValidDate(LocalDate.of(2024, 12, 31))
                .withMinPurchase(50000.0)
                .build();

        CompletableFuture<PromoCode> future = CompletableFuture.completedFuture(promoCode);

        when(promoCodeService.findById(promoCode.getId())).thenReturn(future);

        ResponseEntity<PromoCode> response = promoCodeController.readPromoCodePage(promoCode.getId());

        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody().getId().equals(promoCode.getId()));
    }

    @Test
    void testUpdatePromoCodePost() throws Exception {
        PromoCode promoCode = new PromoCode.Builder()
                .withId(UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194"))
                .withName("PAYDAY50")
                .withDescription("Berlaku tanggal 1-5 setiap bulannya")
                .withValidDate(LocalDate.of(2024, 12, 31))
                .withMinPurchase(50000.0)
                .build();

        ResponseEntity<Void> response = promoCodeController.updatePromoCodePost(promoCode.getId(), promoCode);

        verify(promoCodeService, times(1)).updatePromoCode(promoCode.getId(), promoCode);
        assert(response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    void testDeletePromoCode() throws Exception {
        PromoCode promoCode = new PromoCode.Builder()
                .withId(UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194"))
                .withName("PAYDAY50")
                .withDescription("Berlaku tanggal 1-5 setiap bulannya")
                .withValidDate(LocalDate.of(2024, 12, 31))
                .withMinPurchase(50000.0)
                .build();

        ResponseEntity<Void> response = promoCodeController.deletePromoCode(promoCode.getId());

        verify(promoCodeService, times(1)).deletePromoCode(promoCode.getId());
        assert(response.getStatusCode() == HttpStatus.OK);
    }
}

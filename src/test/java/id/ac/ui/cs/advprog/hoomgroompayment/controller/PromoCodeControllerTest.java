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
        PromoCode promoCode = new PromoCode();
        UUID id = UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194");
        promoCode.setId(id);
        promoCode.setName("PAYDAY50");
        promoCode.setDescription("Berlaku tanggal 1-5 setiap bulannya");
        promoCode.setValidDate(LocalDate.of(2024, 12, 31));
        promoCode.setMinPurchase(Double.valueOf(50000));

        ResponseEntity<PromoCode> response = promoCodeController.createPromoCodePost(promoCode);

        verify(promoCodeService, times(1)).createPromoCode(promoCode);
        assert(response.getStatusCode() == HttpStatus.CREATED);
    }

    @Test
    void testReadPromoCodePage() throws Exception {
        PromoCode promoCode = new PromoCode();
        UUID id = UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194");
        promoCode.setId(id);
        promoCode.setName("PAYDAY50");
        promoCode.setDescription("Berlaku tanggal 1-5 setiap bulannya");
        promoCode.setValidDate(LocalDate.of(2024, 12, 31));
        promoCode.setMinPurchase(Double.valueOf(50000));

        when(promoCodeService.readPromoCodeById(promoCode.getId())).thenReturn(promoCode);

        ResponseEntity<PromoCode> response = promoCodeController.readPromoCodePage(promoCode.getId());

        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody().getId().equals(promoCode.getId()));
    }

    @Test
    void testUpdatePromoCodePost() throws Exception {
        PromoCode promoCode = new PromoCode();
        UUID id = UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194");
        promoCode.setId(id);
        promoCode.setName("PAYDAY50");
        promoCode.setDescription("Berlaku tanggal 1-5 setiap bulannya");
        promoCode.setValidDate(LocalDate.of(2024, 12, 31));
        promoCode.setMinPurchase(Double.valueOf(50000));

        ResponseEntity<Void> response = promoCodeController.updatePromoCodePost(promoCode.getId(), promoCode);

        verify(promoCodeService, times(1)).updatePromoCode(promoCode.getId(), promoCode);
        assert(response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    void testDeletePromoCode() throws Exception {
        PromoCode promoCode = new PromoCode();
        UUID id = UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194");
        promoCode.setId(id);
        promoCode.setName("PAYDAY50");
        promoCode.setDescription("Berlaku tanggal 1-5 setiap bulannya");
        promoCode.setValidDate(LocalDate.of(2024, 12, 31));
        promoCode.setMinPurchase(Double.valueOf(50000));

        ResponseEntity<Void> response = promoCodeController.deletePromoCode(promoCode.getId());

        verify(promoCodeService, times(1)).deletePromoCode(promoCode.getId());
        assert(response.getStatusCode() == HttpStatus.OK);
    }
}

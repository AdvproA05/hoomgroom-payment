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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
        PromoCode promoCode = new PromoCode();
        UUID id = UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194");
        promoCode.setId(id);
        promoCode.setName("PAYDAY50");
        promoCode.setDescription("Berlaku tanggal 1-5 setiap bulannya");
        promoCode.setValidDate(LocalDate.of(2024, 12, 31));
        promoCode.setMinPurchase(Double.valueOf(50000));

        doReturn(promoCodes.iterator()).when(promoCodeRepository).findAll();
        doReturn(promoCode).when(promoCodeRepository).save(promoCode);

        PromoCode result = promoCodeService.createPromoCode(promoCode);
        verify(promoCodeRepository, times(1)).save(promoCode);
        assertEquals(promoCode.getId(), result.getId());
    }

    @Test
    void testCreatePromoCodeInvalid() {
        PromoCode promoCode = new PromoCode();
        UUID id = UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194");
        promoCode.setId(id);
        promoCode.setName("#####");
        promoCode.setDescription("Berlaku tanggal 1-5 setiap bulannya");
        promoCode.setValidDate(LocalDate.of(2024, 12, 31));
        promoCode.setMinPurchase(Double.valueOf(50000));

        doReturn(promoCodes.iterator()).when(promoCodeRepository).findAll();

        assertThrows(IllegalArgumentException.class, () -> promoCodeService.createPromoCode(promoCode));
        verify(promoCodeRepository, times(0)).save(promoCode);
    }

    @Test
    void testReadPromoCode() {
        PromoCode promoCode = new PromoCode();
        UUID id = UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194");
        promoCode.setId(id);
        promoCode.setName("PAYDAY50");
        promoCode.setDescription("Berlaku tanggal 1-5 setiap bulannya");
        promoCode.setValidDate(LocalDate.of(2024, 12, 31));
        promoCode.setMinPurchase(Double.valueOf(50000));

        when(promoCodeRepository.findById(anyString())).thenReturn(promoCode);

        PromoCode retrievedPromoCode = promoCodeService.readPromoCodeById(promoCode.getId());

        assertNotNull(retrievedPromoCode);
        assertEquals(promoCode.getId(), retrievedPromoCode.getId());
    }

    @Test
    void testReadPromoCodeNotExist() {
        UUID nonExistingId = UUID.randomUUID();
        when(promoCodeRepository.findById(anyString())).thenReturn(null);

        PromoCode retrievedPromoCode = promoCodeService.readPromoCodeById(nonExistingId);

        assertNull(retrievedPromoCode);
    }

    @Test
    void testUpdatePromoCode() {
        PromoCode promoCode = new PromoCode();
        UUID id = UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194");
        promoCode.setId(id);
        promoCode.setName("PAYDAY50");
        promoCode.setDescription("Berlaku tanggal 1-5 setiap bulannya");
        promoCode.setValidDate(LocalDate.of(2024, 12, 31));
        promoCode.setMinPurchase(Double.valueOf(50000));

        when(promoCodeRepository.findById(anyString())).thenReturn(promoCode);
        when(promoCodeRepository.save(any(PromoCode.class))).thenReturn(promoCode);

        PromoCode updatedPromoCode = promoCodeService.updatePromoCode(promoCode.getId(), promoCode);

        assertNotNull(updatedPromoCode);
        assertEquals(promoCode.getId(), updatedPromoCode.getId());
    }

    @Test
    void testUpdatePromoCodeNotExist() {
        PromoCode promoCode = new PromoCode();
        UUID nonExistingId = UUID.randomUUID();
        promoCode.setId(nonExistingId);
        promoCode.setName("PAYDAY50");
        promoCode.setDescription("Berlaku tanggal 1-5 setiap bulannya");
        promoCode.setValidDate(LocalDate.of(2024, 12, 31));
        promoCode.setMinPurchase(Double.valueOf(50000));

        when(promoCodeRepository.findById(anyString())).thenReturn(null);
        
        PromoCode updatedPromoCode = promoCodeService.updatePromoCode(nonExistingId, promoCode);

        assertNull(updatedPromoCode);
    }

    @Test
    void testDeletePromoCode() {
        PromoCode promoCode = new PromoCode();
        UUID id = UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194");
        promoCode.setId(id);
        promoCode.setName("PAYDAY50");
        promoCode.setDescription("Berlaku tanggal 1-5 setiap bulannya");
        promoCode.setValidDate(LocalDate.of(2024, 12, 31));
        promoCode.setMinPurchase(Double.valueOf(50000));

        promoCodeService.deletePromoCode(promoCode.getId());
        verify(promoCodeRepository, times(1)).deleteById(promoCode.getId().toString());
    }
}

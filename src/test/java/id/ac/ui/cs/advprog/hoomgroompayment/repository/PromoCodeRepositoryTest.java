package id.ac.ui.cs.advprog.hoomgroompayment.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;

public class PromoCodeRepositoryTest {
    PromoCodeRepository promoCodeRepository;
    List<PromoCode> promoCodes;

    @BeforeEach
    void setUp() {
        promoCodeRepository = new PromoCodeRepository();
        promoCodes = new ArrayList<>();

        PromoCode promoCode1 = new PromoCode();
        UUID id1 = UUID.fromString("4f789ce3-7d9b-4a17-a5dc-903a8aebd194");
        promoCode1.setId(id1);
        promoCode1.setName("PAYDAY50");
        promoCode1.setDescription("Berlaku tanggal 1-5 setiap bulannya");
        promoCode1.setValidDate(LocalDate.of(2024, 12, 31));
        promoCode1.setMinPurchase(Double.valueOf(50000));

        PromoCode promoCode2 = new PromoCode();
        UUID id2 = UUID.fromString("8ee99d28-3e4c-4d7a-a4c6-87c240fa96b9");
        promoCode2.setId(id2);
        promoCode2.setName("BIRTHDAY30");
        promoCode2.setDescription("Diskon di hari ulang tahunmu!");
        promoCode2.setValidDate(LocalDate.of(2025, 12, 31));
        promoCode2.setMinPurchase(Double.valueOf(100000));

        promoCodes.add(promoCode1);
        promoCodes.add(promoCode2);
    }

    @Test
    void testSavePromoCode() {
        PromoCode promoCode = promoCodes.get(0);
        PromoCode result = promoCodeRepository.save(promoCode);

        assertEquals(promoCode.getId(), result.getId());
        assertEquals(promoCode.getId(), result.getId());
        assertEquals(promoCode.getName(), result.getName());
        assertEquals(promoCode.getDescription(), result.getDescription());
        assertEquals(promoCode.getValidDate(), result.getValidDate());
        assertEquals(promoCode.getMinPurchase(), result.getMinPurchase());
    }

    @Test
    void testSavePromoCodeUpdate() {
        PromoCode promoCode = promoCodes.get(0);
        promoCodeRepository.save(promoCode);
        PromoCode newPromoCode = promoCodes.get(1);
        newPromoCode.setMinPurchase(Double.valueOf(50000));
        PromoCode result = promoCodeRepository.save(newPromoCode);

        assertEquals(promoCode.getMinPurchase(), result.getMinPurchase());
        assertEquals(newPromoCode.getMinPurchase(), result.getMinPurchase());
    }

    @Test
    void testFindByIdIfFound() {
        for (PromoCode promoCode : promoCodes) {
            promoCodeRepository.save(promoCode);
        }

        PromoCode findResult = promoCodeRepository.findById(promoCodes.get(0).getId().toString());
        assertEquals(promoCodes.get(0).getId(), findResult.getId());
        assertEquals(promoCodes.get(0).getName(), findResult.getName());
        assertEquals(promoCodes.get(0).getDescription(), findResult.getDescription());
        assertEquals(promoCodes.get(0).getValidDate(), findResult.getValidDate());
        assertEquals(promoCodes.get(0).getMinPurchase(), findResult.getMinPurchase());
    }

    @Test
    void testFindByIdIfNotFound() {
        for (PromoCode promoCode : promoCodes) {
            promoCodeRepository.save(promoCode);
        }

        PromoCode findResult = promoCodeRepository.findById("1f067c51-0d32-5b48-8ef8-31feba4c7e8a");
        assertNull(findResult);
    }

    @Test
    void testDeleteById() {
        PromoCode promoCode = promoCodes.get(0);
        promoCodeRepository.save(promoCode);

        promoCodeRepository.deleteById(promoCode.getId().toString());
        Iterator<PromoCode> result = promoCodeRepository.findAll();
        assertFalse(result.hasNext());
    }

    @Test
    void testFindAll() {
        for (PromoCode promoCode : promoCodes) {
            promoCodeRepository.save(promoCode);
        }

        Iterator<PromoCode> results = promoCodeRepository.findAll();
        List<PromoCode> resultList = new ArrayList<>();
        resultList.add(results.next());
        resultList.add(results.next());

        assertEquals(2, resultList.size());
    }
}

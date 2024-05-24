package id.ac.ui.cs.advprog.hoomgroompayment.repository;

import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PromoCodeRepositoryTest {

    @Mock
    private PromoCodeRepository promoCodeRepository;

    @InjectMocks
    private PromoCode.Builder promoCodeBuilder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        promoCodeBuilder = new PromoCode.Builder();
    }

    @Test
    public void shouldFindPromoCodeByName() {
        String name = "PAYDAY50";
        PromoCode promoCode = promoCodeBuilder
                .withId(UUID.randomUUID())
                .withName(name)
                .withDescription("Description")
                .withValidDate(LocalDate.now())
                .withMinPurchase(50.0)
                .build();
        when(promoCodeRepository.findByName(name)).thenReturn(Optional.of(promoCode));

        Optional<PromoCode> foundPromoCode = promoCodeRepository.findByName(name);

        assertTrue(foundPromoCode.isPresent());
        assertEquals(name, foundPromoCode.get().getName());
    }

    @Test
    public void shouldNotFindPromoCodeByNonExistentName() {
        String name = "NON_EXISTENT_PROMO";
        when(promoCodeRepository.findByName(name)).thenReturn(Optional.empty());

        Optional<PromoCode> foundPromoCode = promoCodeRepository.findByName(name);

        assertTrue(foundPromoCode.isEmpty());
    }
}

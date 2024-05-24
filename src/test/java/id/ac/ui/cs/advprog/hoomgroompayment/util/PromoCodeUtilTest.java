package id.ac.ui.cs.advprog.hoomgroompayment.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromoCodeUtilTest {

    @Test
    void extractDiscountPercentage_ReturnsCorrectPercentage_WhenPromoCodeHasPercentage() {
        String promoCode = "DISCOUNT10";
        double expected = 10.0;
        double actual = PromoCodeUtil.extractDiscountPercentage(promoCode);
        assertEquals(expected, actual);
    }

    @Test
    void extractDiscountPercentage_ReturnsZero_WhenPromoCodeHasNoPercentage() {
        String promoCode = "DISCOUNT";
        double expected = 0.0;
        double actual = PromoCodeUtil.extractDiscountPercentage(promoCode);
        assertEquals(expected, actual);
    }
    @Test
    void extractDiscountPercentage_ReturnsZero_WhenPromoCodeIsEmpty() {
        String promoCode = "";
        double expected = 0.0;
        double actual = PromoCodeUtil.extractDiscountPercentage(promoCode);
        assertEquals(expected, actual);
    }
}
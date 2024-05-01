package id.ac.ui.cs.advprog.hoomgroompayment.service;

import java.util.UUID;

import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;

public interface PromoCodeService {
    PromoCode createPromoCode(PromoCode promoCode);
    PromoCode readPromoCodeById(UUID id);
    PromoCode updatePromoCode(UUID id, PromoCode promoCode);
    void deletePromoCode(UUID id);
}

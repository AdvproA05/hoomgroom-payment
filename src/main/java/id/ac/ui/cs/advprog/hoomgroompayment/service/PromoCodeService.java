package id.ac.ui.cs.advprog.hoomgroompayment.service;

import java.util.List;
import java.util.UUID;

import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;
import id.ac.ui.cs.advprog.hoomgroompayment.repository.PromoCodeRepository;
import org.springframework.stereotype.Service;

public interface PromoCodeService {
    PromoCode createPromoCode(PromoCode promoCode);
    List<PromoCode> findAll();
    PromoCode findById(UUID id);
    PromoCode updatePromoCode(UUID id, PromoCode promoCode);
    void deletePromoCode(UUID id);
}

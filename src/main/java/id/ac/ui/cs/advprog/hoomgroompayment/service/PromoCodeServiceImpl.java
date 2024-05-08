package id.ac.ui.cs.advprog.hoomgroompayment.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Iterator;

import id.ac.ui.cs.advprog.hoomgroompayment.service.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;
import id.ac.ui.cs.advprog.hoomgroompayment.repository.PromoCodeRepository;

@Service
public class PromoCodeServiceImpl implements PromoCodeService {
    @Autowired
    private PromoCodeRepository promoCodeRepository;

    @Override
    public PromoCode createPromoCode(PromoCode promoCode) {
        if (!isAlphanumeric(promoCode.getName())) {
            throw new IllegalArgumentException();
        }

        if (promoCode.getDescription() == null || promoCode.getDescription().isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (promoCode.getMinPurchase() <= Double.valueOf(0)) {
            throw new IllegalArgumentException();
        }

        PromoCode savedPromoCode = promoCodeRepository.save(promoCode);
//        if (savedPromoCode == null) {
//            throw new IllegalStateException("Failed to save promo code");
//        }

        return savedPromoCode;
    }

    @Override
    public List<PromoCode> findAll() {
        return promoCodeRepository.findAll();
    }

    @Override
    public PromoCode findById(UUID id) {
        Optional<PromoCode> existingPromoCode = promoCodeRepository.findById(id);
        return existingPromoCode != null ? existingPromoCode.orElse(null) : null;
    }

    @Override
    public PromoCode updatePromoCode(UUID id, PromoCode promoCode) {
        Optional<PromoCode> existingPromoCode = promoCodeRepository.findById(id);
        if (existingPromoCode != null) {
            promoCode.setId(id);
            return promoCodeRepository.save(promoCode);
        }
        return null;
    }

    @Override
    public void deletePromoCode(UUID id) {
        promoCodeRepository.deleteById(id);
    }

    private boolean isAlphanumeric(String name) {
        return name != null && name.matches("[A-Z0-9]+");
    }
}

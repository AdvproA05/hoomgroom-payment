package id.ac.ui.cs.advprog.hoomgroompayment.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;
import id.ac.ui.cs.advprog.hoomgroompayment.repository.PromoCodeRepository;

@Service
public class PromoCodeServiceImpl implements PromoCodeService {
    @Autowired
    private PromoCodeRepository promoCodeRepository;

    @Override
    public CompletableFuture<PromoCode> createPromoCode(PromoCode promoCode) {
        return CompletableFuture.supplyAsync(() -> {
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

            return savedPromoCode;
        });
    }

    @Override
    public List<PromoCode> findAll() {
        return promoCodeRepository.findAll();
    }

    @Override
    public CompletableFuture<PromoCode> findById(UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<PromoCode> existingPromoCode = promoCodeRepository.findById(id);
            return existingPromoCode.orElse(null);
        });
    }

    @Override
    public CompletableFuture<PromoCode> findByName(PromoCode promoCode) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<PromoCode> existingPromoCode = promoCodeRepository.findByName(promoCode.getName());
            return existingPromoCode.orElse(null);
        });
    }

    @Override
    public CompletableFuture<PromoCode> updatePromoCode(UUID id, PromoCode promoCode) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<PromoCode> existingPromoCode = promoCodeRepository.findById(id);
            if (existingPromoCode.isPresent()) {
                promoCode.setId(id);
                return promoCodeRepository.save(promoCode);
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Void> deletePromoCode(UUID id) {
        return CompletableFuture.runAsync(() -> promoCodeRepository.deleteById(id));
    }

    private boolean isAlphanumeric(String name) {
        return name != null && name.matches("[A-Z0-9]+");
    }
}

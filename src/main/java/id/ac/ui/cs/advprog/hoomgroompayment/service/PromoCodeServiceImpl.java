package id.ac.ui.cs.advprog.hoomgroompayment.service;

import java.util.UUID;
import java.util.Iterator;

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
        if (!isValid(promoCode.getName())) {
            throw new IllegalArgumentException();
        }

        if (promoCode.getDescription() == null || promoCode.getDescription().isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (promoCode.getMinPurchase() <= Double.valueOf(0)) {
            throw new IllegalArgumentException();
        }

        promoCode.setId(UUID.randomUUID());
        return promoCodeRepository.save(promoCode);
    }

    @Override
    public PromoCode readPromoCodeById(UUID id) {
        return promoCodeRepository.findById(id.toString());
    }

    @Override
    public PromoCode updatePromoCode(UUID id, PromoCode promoCode) {
        PromoCode existingPromoCode = promoCodeRepository.findById(id.toString());
        if (existingPromoCode != null) {
            promoCode.setId(id);
            return promoCodeRepository.save(promoCode);
        }
        return null;
    }

    @Override
    public void deletePromoCode(UUID id) {
        promoCodeRepository.deleteById(id.toString());
    }

    private boolean isValid(String name) {
        boolean alphanumeric = name != null && name.matches("[A-Z0-9]+");
        boolean unique = true;
        
        Iterator<PromoCode> promoCodes = promoCodeRepository.findAll();
        if (!promoCodes.hasNext()) {
            unique = true;
        }
        while (promoCodes.hasNext()) {
            if (promoCodes.next().getName().equals(name)) {
                unique = false;
            }
        }

        return alphanumeric && unique;
    }
}

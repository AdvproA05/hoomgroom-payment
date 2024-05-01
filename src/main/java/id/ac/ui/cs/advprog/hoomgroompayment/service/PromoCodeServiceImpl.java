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
    public PromoCode createPromoCode(PromoCode promoCode) {return null;}

    @Override
    public PromoCode readPromoCodeById(UUID id) {return null;}

    @Override
    public PromoCode updatePromoCode(UUID id, PromoCode promoCode) {return null;}

    @Override
    public void deletePromoCode(UUID id) {}

    private boolean isValid(String name) {return true;}
}

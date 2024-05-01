package id.ac.ui.cs.advprog.hoomgroompayment.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;

public class PromoCodeRepository {
    private List<PromoCode> promoCodeData = new ArrayList<>();

    public PromoCode save(PromoCode promoCode) {
        for (PromoCode savedPromo : promoCodeData) {
            if (savedPromo.getId().equals(promoCode.getId())) {
                promoCodeData.remove(savedPromo);
                break;
            }
        }
        promoCodeData.add(promoCode);
        return promoCode;
    }

    public PromoCode findById(String id) {
        for (PromoCode savedPromo : promoCodeData) {
            if (id.equals(savedPromo.getId().toString())) {
                return savedPromo;
            }
        }
        return null;
    }

    public PromoCode deleteById(String id) {
        PromoCode promoCode = findById(id);
        if (promoCode != null) {
            promoCodeData.remove(promoCode);
        }
        return promoCode;
    }

    public Iterator<PromoCode> findAll() {
        return promoCodeData.iterator();
    }
}

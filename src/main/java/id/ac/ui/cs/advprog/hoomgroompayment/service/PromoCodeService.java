package id.ac.ui.cs.advprog.hoomgroompayment.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;

public interface PromoCodeService {
    CompletableFuture<PromoCode> createPromoCode(PromoCode promoCode);
    CompletableFuture<List<PromoCode>>  findAll();
    CompletableFuture<PromoCode> findById(UUID id);
    CompletableFuture<PromoCode> findByName(PromoCode promoCode);
    CompletableFuture<PromoCode> updatePromoCode(UUID id, PromoCode promoCode);
    CompletableFuture<Void> deletePromoCode(UUID id);
}

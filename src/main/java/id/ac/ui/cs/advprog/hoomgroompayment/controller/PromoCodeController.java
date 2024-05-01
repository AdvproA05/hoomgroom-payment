package id.ac.ui.cs.advprog.hoomgroompayment.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;
import id.ac.ui.cs.advprog.hoomgroompayment.service.PromoCodeService;

@RestController
@RequestMapping("/promos")
public class PromoCodeController {

    @Autowired
    PromoCodeService promoCodeService;
  
    @PostMapping
    public ResponseEntity<PromoCode> createPromoCodePost(@RequestBody PromoCode promoCode) {
      try {
          promoCodeService.createPromoCode(promoCode);
          return ResponseEntity.status(HttpStatus.CREATED).build();
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
    }

    @GetMapping("/{id_promo}")
    public ResponseEntity<PromoCode> readPromoCodePage(@PathVariable UUID id_promo) {
      try {
          PromoCode promoCode = promoCodeService.readPromoCodeById(id_promo);
          if (promoCode != null) {
              return ResponseEntity.ok(promoCode);
          } else {
              return ResponseEntity.notFound().build();
          }
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
    }
  
    @PutMapping("/{id_promo}")
    public ResponseEntity<Void> updatePromoCodePost(@PathVariable UUID id_promo, @RequestBody PromoCode promoCode) {
      try {
          promoCodeService.updatePromoCode(id_promo, promoCode);
          return ResponseEntity.ok().build();
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
    }

    @DeleteMapping("/{id_promo}")
    public ResponseEntity<Void> deletePromoCode(@PathVariable UUID id_promo) {
      try {
          promoCodeService.deletePromoCode(id_promo);
          return ResponseEntity.ok().build();
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
    }
}

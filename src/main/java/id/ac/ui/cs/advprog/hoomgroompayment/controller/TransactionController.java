package id.ac.ui.cs.advprog.hoomgroompayment.controller;

import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;
import id.ac.ui.cs.advprog.hoomgroompayment.model.Transaction;
import id.ac.ui.cs.advprog.hoomgroompayment.service.TransactionService;
import id.ac.ui.cs.advprog.hoomgroompayment.util.PromoCodeUtil;
import id.ac.ui.cs.advprog.hoomgroompayment.service.invoker.TransactionInvoker;
import id.ac.ui.cs.advprog.hoomgroompayment.service.receiver.TopUpReceiver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private TransactionInvoker transactionInvoker;
    private TopUpReceiver topUpReceiver;
    double finalPrice;
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService, TransactionInvoker transactionInvoker, TopUpReceiver topUpReceiver) {
        this.transactionService = transactionService;
        this.transactionInvoker = transactionInvoker;
        this.topUpReceiver = topUpReceiver;
    }


    @PostMapping("/purchase")
    public CompletableFuture<ResponseEntity<?>> purchaseProduct(HttpServletRequest request, @RequestBody double price,  @RequestParam(required = false) PromoCode promoCode) {
        return topUpReceiver.getUserDetails(request).thenCompose(userDetails -> {
            if (userDetails != null) {
                // Check if promoCode is provided and valid
                if (promoCode != null && isValidPromoCode(promoCode)) {
                    double discountPercentage = PromoCodeUtil.extractDiscountPercentage(promoCode.getName());
                    // Apply promo code
                    finalPrice = applyPromoCode(price, discountPercentage);
                } else {
                    finalPrice = price;
                }

                return transactionInvoker.updateUserBalance(userDetails, finalPrice)
                        .thenApply(updatedUserDetails -> ResponseEntity.ok().body(updatedUserDetails))
                        .handle((response, ex) -> {
                            if (ex != null) {
                                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
                            }
                            return response;
                        });
            } else {
                return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Bad Request"));
            }
        });
    }

    private boolean isValidPromoCode(PromoCode promoCode) {
        // Implement your validation logic here
        // For example, check if promo code is valid based on its validDate and minPurchase
        return promoCode.getValidDate().isAfter(LocalDate.now()) && promoCode.getMinPurchase() <= 0;
    }

    private double applyPromoCode(Double price, double discountPercentage) {
        return price - (price * discountPercentage / 100);
    }

    @GetMapping("/list")
    public CompletableFuture<ResponseEntity<?>> getTransactionsList(HttpServletRequest request) {
        return topUpReceiver.getUserDetails(request).thenCompose(userDetails -> {
            if (userDetails != null) {
                List<Transaction> transactions = transactionService.findAllByUserId(userDetails.getId());
                return CompletableFuture.completedFuture(ResponseEntity.ok().body(transactions));
            } else {
                return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Bad Request"));
            }
        });
    }

}

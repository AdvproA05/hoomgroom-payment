package id.ac.ui.cs.advprog.hoomgroompayment.service.receiver;

import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;

import java.util.concurrent.CompletableFuture;

public interface TransactionReceiver {
    CompletableFuture<UserDetails> updateUserBalance(String username, Double amount);
}

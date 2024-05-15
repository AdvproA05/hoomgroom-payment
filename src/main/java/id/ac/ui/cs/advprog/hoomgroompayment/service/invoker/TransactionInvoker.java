package id.ac.ui.cs.advprog.hoomgroompayment.service.invoker;

import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;

import java.util.concurrent.CompletableFuture;

public interface TransactionInvoker {
    CompletableFuture<UserDetails> updateUserBalance(UserDetails userDetails, Double amount);
}

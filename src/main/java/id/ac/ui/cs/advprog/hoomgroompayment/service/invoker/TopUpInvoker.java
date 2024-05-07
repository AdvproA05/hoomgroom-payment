package id.ac.ui.cs.advprog.hoomgroompayment.service.invoker;

import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import jakarta.servlet.http.HttpServletRequest;

import java.util.concurrent.CompletableFuture;

public interface TopUpInvoker {
    CompletableFuture<UserDetails> getUserDetails(HttpServletRequest request);
    CompletableFuture<TopUp> insertTopUp(TopUp data);
}
package id.ac.ui.cs.advprog.hoomgroompayment.service.receiver;

import jakarta.servlet.http.HttpServletRequest;
import id.ac.ui.cs.advprog.hoomgroompayment.model.*;

import java.util.concurrent.CompletableFuture;

public interface TopUpReceiver {
    CompletableFuture<TopUp> insertTopUp(TopUp data);
}
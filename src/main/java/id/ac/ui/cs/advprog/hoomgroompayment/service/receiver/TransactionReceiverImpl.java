package id.ac.ui.cs.advprog.hoomgroompayment.service.receiver;

import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransactionReceiverImpl implements TransactionReceiver {

    private UserDetailsRepository userDetailsRepository;

    @Override
    public CompletableFuture<UserDetails> updateUserBalance(String username, Double amount) {
        return CompletableFuture.supplyAsync(() -> {
            UserDetails user = userDetailsRepository.findByUsername(username);
            if (user.getWalletBalance() < amount) {
                throw new RuntimeException("Insufficient balance");
            }
            user.setWalletBalance(user.getWalletBalance() - amount);
            return userDetailsRepository.save(user);
        });
    }
}
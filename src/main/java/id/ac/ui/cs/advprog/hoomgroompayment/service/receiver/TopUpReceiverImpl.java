package id.ac.ui.cs.advprog.hoomgroompayment.service.receiver;

import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.repository.TopUpRepository;
import id.ac.ui.cs.advprog.hoomgroompayment.repository.UserDetailsRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

@Service
public class TopUpReceiverImpl implements TopUpReceiver {
    private TopUpRepository topUpRepository;
    private UserDetailsRepository userDetailsRepository;

    @Override
    public CompletableFuture<TopUp> insertTopUp(TopUp data) {
        if (data == null || data.getTimestamp() == null || data.getUsername() == null) {
            return CompletableFuture.completedFuture(null);
        }

        return CompletableFuture.supplyAsync(() -> {
            Optional<TopUp> optionalTimestamp = topUpRepository.findByTimestampAndUsername(data.getTimestamp(), data.getUsername());
            return optionalTimestamp.orElseGet(() -> topUpRepository.save(data));
        });
    }
}
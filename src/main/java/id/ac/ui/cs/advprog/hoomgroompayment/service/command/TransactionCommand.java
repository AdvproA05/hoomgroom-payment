package id.ac.ui.cs.advprog.hoomgroompayment.service.command;

import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.service.receiver.TopUpReceiverImpl;
import id.ac.ui.cs.advprog.hoomgroompayment.service.receiver.TransactionReceiverImpl;
import org.apache.catalina.User;

import java.util.concurrent.CompletableFuture;

public class TransactionCommand implements Command<UserDetails> {
    private final TransactionReceiverImpl transactionReceiver;
    private final double amount;
    public TransactionCommand(TransactionReceiverImpl transactionReceiver, double amount) {
        this.transactionReceiver = transactionReceiver;
        this.amount = amount;
    }

    @Override
    public CompletableFuture<UserDetails> execute(UserDetails userDetails) {
        if (userDetails == null) {
            return CompletableFuture.completedFuture(null);
        }
        return transactionReceiver.updateUserBalance(userDetails.getUsername(), amount);
    }
}

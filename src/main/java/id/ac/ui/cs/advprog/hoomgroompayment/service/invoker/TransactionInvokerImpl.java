package id.ac.ui.cs.advprog.hoomgroompayment.service.invoker;

import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.service.command.TransactionCommand;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransactionInvokerImpl implements TransactionInvoker {

    private final TransactionCommand transactionCommand;

    public TransactionInvokerImpl (TransactionCommand transactionCommand) {
        this.transactionCommand = transactionCommand;
    }

    @Override
    public CompletableFuture<UserDetails> updateUserBalance(UserDetails userDetails, Double amount) {
        return transactionCommand.execute(userDetails);
    }
}

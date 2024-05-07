package id.ac.ui.cs.advprog.hoomgroompayment.service.command;

import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.service.receiver.TopUpReceiverImpl;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class InsertTopUpCommand implements Command<TopUp> {
    private final TopUpReceiverImpl topUpReceiver;

    public InsertTopUpCommand(TopUpReceiverImpl topUpReceiver) {
        this.topUpReceiver = topUpReceiver;
    }

    @Override
    public CompletableFuture<TopUp> execute(TopUp data) {
        return topUpReceiver.insertTopUp(data);
    }
}
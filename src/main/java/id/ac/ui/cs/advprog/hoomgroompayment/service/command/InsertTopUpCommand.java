package id.ac.ui.cs.advprog.hoomgroompayment.service.command;

import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.service.receiver.TopUpReceiverImpl;
import org.springframework.stereotype.Component;

@Component
public class InsertTopUpCommand implements Command {
    private final TopUpReceiverImpl topUpReceiver;

    public InsertTopUpCommand(TopUpReceiverImpl topUpReceiver) {
        this.topUpReceiver = topUpReceiver;
    }

    @Override
    public TopUp execute(Object data) {
        return this.topUpReceiver.insertTopUp((TopUp) data);
    }
}
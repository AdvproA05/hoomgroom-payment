package id.ac.ui.cs.advprog.hoomgroompayment.service.command;

import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.service.receiver.TopUpReceiverImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class GetUserDetailsCommand implements Command<UserDetails> {
    private final TopUpReceiverImpl topUpReceiver;

    public GetUserDetailsCommand(TopUpReceiverImpl topUpReceiver) {
        this.topUpReceiver = topUpReceiver;
    }

    @Override
    public CompletableFuture<UserDetails> execute(UserDetails request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        return topUpReceiver.getUserDetails(httpRequest);
    }
}
package id.ac.ui.cs.advprog.hoomgroompayment.service.command;

import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.service.receiver.TopUpReceiverImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class GetUserDetailsCommand implements Command {
    private final TopUpReceiverImpl topUpReceiver;

    public GetUserDetailsCommand(TopUpReceiverImpl topUpReceiver) {
        this.topUpReceiver = topUpReceiver;
    }

    @Override
    public UserDetails execute(Object request) {
        return this.topUpReceiver.getUserDetails((HttpServletRequest) request);
    }
}

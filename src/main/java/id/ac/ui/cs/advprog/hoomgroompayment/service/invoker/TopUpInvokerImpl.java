package id.ac.ui.cs.advprog.hoomgroompayment.service.invoker;

import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.service.command.GetUserDetailsCommand;
import id.ac.ui.cs.advprog.hoomgroompayment.service.command.InsertTopUpCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class TopUpInvokerImpl implements TopUpInvoker {
    private final GetUserDetailsCommand getUserDetailsCommand;
    private final InsertTopUpCommand insertTopUpCommand;

    public TopUpInvokerImpl(GetUserDetailsCommand getUserDetailsCommand, InsertTopUpCommand insertTopUpCommand) {
        this.getUserDetailsCommand = getUserDetailsCommand;
        this.insertTopUpCommand = insertTopUpCommand;
    }

    @Override
    public UserDetails getUserDetails(HttpServletRequest request) {
        return this.getUserDetailsCommand.execute(request);
    }

    @Override
    public TopUp insertDeposit(TopUp data) {
        return this.insertTopUpCommand.execute(data);
    }
}

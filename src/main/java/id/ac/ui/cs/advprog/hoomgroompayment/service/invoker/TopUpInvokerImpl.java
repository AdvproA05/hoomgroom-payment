package id.ac.ui.cs.advprog.hoomgroompayment.service.invoker;

import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.service.command.GetUserDetailsCommand;
import id.ac.ui.cs.advprog.hoomgroompayment.service.command.InsertTopUpCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TopUpInvokerImpl implements TopUpInvoker {
    private final GetUserDetailsCommand getUserDetailsCommand;
    private final InsertTopUpCommand insertTopUpCommand;

    public TopUpInvokerImpl(GetUserDetailsCommand getUserDetailsCommand, InsertTopUpCommand insertTopUpCommand) {
        this.getUserDetailsCommand = getUserDetailsCommand;
        this.insertTopUpCommand = insertTopUpCommand;
    }

    @Override
    public CompletableFuture<UserDetails> getUserDetails(HttpServletRequest request) {
        return getUserDetailsCommand.execute((UserDetails) request);
    }

    @Override
    public CompletableFuture<TopUp> insertTopUp(TopUp data) {
        return insertTopUpCommand.execute(data);
    }
}
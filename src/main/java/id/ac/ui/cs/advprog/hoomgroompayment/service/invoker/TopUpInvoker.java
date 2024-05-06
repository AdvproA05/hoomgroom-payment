package id.ac.ui.cs.advprog.hoomgroompayment.service.invoker;

import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import jakarta.servlet.http.HttpServletRequest;

public interface TopUpInvoker {
    UserDetails getUserDetails(HttpServletRequest request);
    TopUp insertDeposit(TopUp data);
}

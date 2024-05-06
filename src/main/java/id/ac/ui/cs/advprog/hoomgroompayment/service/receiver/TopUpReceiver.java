package id.ac.ui.cs.advprog.hoomgroompayment.service.receiver;

import jakarta.servlet.http.HttpServletRequest;
import id.ac.ui.cs.advprog.hoomgroompayment.model.*;

public interface TopUpReceiver {
    UserDetails getUserDetails(HttpServletRequest request);
    TopUp insertTopUp(TopUp data);
}
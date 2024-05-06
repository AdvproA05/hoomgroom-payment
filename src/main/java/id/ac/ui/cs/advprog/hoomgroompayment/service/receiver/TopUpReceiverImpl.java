package id.ac.ui.cs.advprog.hoomgroompayment.service.receiver;

import id.ac.ui.cs.advprog.hoomgroompayment.security.jwt.JwtUtils;
import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.repository.TopUpRepository;
import id.ac.ui.cs.advprog.hoomgroompayment.repository.UserDetailsRepository;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TopUpReceiverImpl implements TopUpReceiver {
    private TopUpRepository topUpRepository;
    private JwtUtils jwtUtils;
    private UserDetailsRepository userDetailsRepository;
    @Override
    public UserDetails getUserDetails(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String username_loggedIn = jwtUtils.getUserNameFromJwtToken(token);


        return userDetailsRepository.findByUsername(username_loggedIn);
    }
    @Override
    public TopUp insertTopUp(TopUp data) {
        if (data == null || data.getTimestamp() == null || data.getUsername() == null) {
            return null;
        }

        Optional<TopUp> optionalTimestamp = this.topUpRepository.findByTimestampAndUsername(data.getTimestamp(), data.getUsername());
        return optionalTimestamp.orElse(this.topUpRepository.save(data));
    }
}

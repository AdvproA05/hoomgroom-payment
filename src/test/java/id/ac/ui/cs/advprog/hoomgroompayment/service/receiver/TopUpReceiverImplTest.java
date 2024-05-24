package id.ac.ui.cs.advprog.hoomgroompayment.service.receiver;

import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.repository.TopUpRepository;
import id.ac.ui.cs.advprog.hoomgroompayment.repository.UserDetailsRepository;
import id.ac.ui.cs.advprog.hoomgroompayment.security.jwt.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TopUpReceiverImplTest {

    @Mock
    private TopUpRepository topUpRepository;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private TopUpReceiverImpl topUpReceiver;

    @BeforeEach
    void setUp() {
        // Inisialisasi mock
        when(request.getHeader("Authorization")).thenReturn("dummyToken");
    }

    @Test
    void getUserDetails_ReturnsUserDetails() {
        // Pengaturan mock
        String username = "dummyUser";
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername(username);
        when(jwtUtils.getUserNameFromJwtToken("dummyToken")).thenReturn(username);
        when(userDetailsRepository.findByUsername(username)).thenReturn(userDetails);

        // Panggil metode yang akan diuji
        CompletableFuture<UserDetails> userDetailsFuture = topUpReceiver.getUserDetails(request);
        UserDetails result = userDetailsFuture.join();

        // Periksa hasilnya
        assertEquals(username, result.getUsername());
    }

    @Test
    void insertTopUp_InsertsTopUp() {
        // Pengaturan mock
        TopUp topUp = new TopUp();
        topUp.setTimestamp(new Date());
        topUp.setUsername("dummyUser");
        when(topUpRepository.findByTimestampAndUsername(topUp.getTimestamp(), topUp.getUsername())).thenReturn(Optional.empty());
        when(topUpRepository.save(topUp)).thenReturn(topUp);

        // Panggil metode yang akan diuji
        CompletableFuture<TopUp> topUpFuture = topUpReceiver.insertTopUp(topUp);
        TopUp result = topUpFuture.join();

        // Periksa hasilnya
        assertEquals(topUp, result);
    }
}

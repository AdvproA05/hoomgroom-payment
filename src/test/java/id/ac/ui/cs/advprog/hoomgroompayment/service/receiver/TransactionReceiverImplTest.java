package id.ac.ui.cs.advprog.hoomgroompayment.service.receiver;

import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.repository.UserDetailsRepository;
import id.ac.ui.cs.advprog.hoomgroompayment.service.receiver.TransactionReceiverImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionReceiverImplTest {

    @Mock
    UserDetailsRepository userDetailsRepository;

    @InjectMocks
    TransactionReceiverImpl transactionReceiver;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void updateUserBalanceShouldUpdateBalanceWhenSufficientFunds() throws ExecutionException, InterruptedException {
        UserDetails user = new UserDetails();
        user.setUsername("testUser");
        user.setWalletBalance(200.0);

        when(userDetailsRepository.findByUsername("testUser")).thenReturn(user);
        when(userDetailsRepository.save(user)).thenReturn(user);

        UserDetails updatedUser = transactionReceiver.updateUserBalance("testUser", 100.0).get();

        assertEquals(100.0, updatedUser.getWalletBalance());
    }

    @Test
    public void updateUserBalanceShouldThrowExceptionWhenInsufficientFunds() {
        UserDetails user = new UserDetails();
        user.setUsername("testUser");
        user.setWalletBalance(50.0);

        when(userDetailsRepository.findByUsername("testUser")).thenReturn(user);

        assertThrows(ExecutionException.class, () -> transactionReceiver.updateUserBalance("testUser", 100.0).get());
    }

    @Test
    public void updateUserBalanceShouldThrowExceptionWhenUserNotFound() {
        when(userDetailsRepository.findByUsername("testUser")).thenReturn(null);

        assertThrows(ExecutionException.class, () -> transactionReceiver.updateUserBalance("testUser", 100.0).get());
    }
}

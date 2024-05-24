package id.ac.ui.cs.advprog.hoomgroompayment.service.command;

import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.service.receiver.TransactionReceiverImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class TransactionCommandTest {
    private UserDetails userDetails;
    @Mock
    private TransactionReceiverImpl transactionReceiver;

    private TransactionCommand transactionCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionReceiver = Mockito.mock(TransactionReceiverImpl.class);
        transactionCommand = new TransactionCommand(transactionReceiver, 100.0); // Pass necessary arguments here
    }

    @Test
    public void execute_shouldUpdateUserBalance() {
        userDetails = new UserDetails();
        userDetails.setUsername("testUser");

        when(transactionReceiver.updateUserBalance(anyString(), anyDouble())).thenReturn(CompletableFuture.completedFuture(userDetails));

        CompletableFuture<UserDetails> result = transactionCommand.execute(userDetails);

        assertEquals(userDetails, result.join());
    }

    @Test
    public void execute_shouldHandleNullUserDetails() {
        userDetails = new UserDetails();
        userDetails.setUsername("testUser");

        when(transactionReceiver.updateUserBalance(anyString(), anyDouble())).thenReturn(CompletableFuture.completedFuture(null));

        CompletableFuture<UserDetails> result = transactionCommand.execute(null);

        assertEquals(null, result.join());
    }
}
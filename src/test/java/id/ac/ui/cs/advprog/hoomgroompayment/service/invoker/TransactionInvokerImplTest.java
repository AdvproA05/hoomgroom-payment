package id.ac.ui.cs.advprog.hoomgroompayment.service.invoker;
import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.service.command.TransactionCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransactionInvokerImplTest {

    @Mock
    private TransactionCommand transactionCommand;

    private TransactionInvokerImpl transactionInvoker;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionInvoker = new TransactionInvokerImpl(transactionCommand);
    }

    @Test
    public void updateUserBalance_shouldReturnUpdatedUserDetails() {
        UserDetails userDetails = new UserDetails();
        userDetails.setWalletBalance(100.0);
        when(transactionCommand.execute(userDetails)).thenReturn(CompletableFuture.completedFuture(userDetails));

        CompletableFuture<UserDetails> result = transactionInvoker.updateUserBalance(userDetails, 50.0);

        assertEquals(100.0, result.join().getWalletBalance());
        verify(transactionCommand, times(1)).execute(userDetails);
    }

    @Test
    public void updateUserBalance_shouldHandleNullUserDetails() {
        when(transactionCommand.execute(null)).thenReturn(CompletableFuture.completedFuture(null));

        CompletableFuture<UserDetails> result = transactionInvoker.updateUserBalance(null, 50.0);

        assertEquals(null, result.join());
        verify(transactionCommand, times(1)).execute(null);
    }
}

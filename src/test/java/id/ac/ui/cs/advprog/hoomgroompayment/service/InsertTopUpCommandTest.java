package id.ac.ui.cs.advprog.hoomgroompayment.service;

import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.service.command.InsertTopUpCommand;
import id.ac.ui.cs.advprog.hoomgroompayment.service.receiver.TopUpReceiverImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class InsertTopUpCommandTest {

    @Mock
    private TopUpReceiverImpl topUpReceiver;

    @InjectMocks
    private InsertTopUpCommand insertTopUpCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute_Success() {
        // Given
        TopUp topUp = new TopUp();
        when(topUpReceiver.insertTopUp(topUp)).thenReturn(CompletableFuture.completedFuture(topUp));

        // When
        TopUp result = insertTopUpCommand.execute(topUp).join();

        // Then
        assertEquals(topUp, result);
    }

    @Test
    public void testExecute_Failure() {
        // Given
        TopUp topUp = null;
        when(topUpReceiver.insertTopUp(topUp)).thenReturn(CompletableFuture.completedFuture(null));

        // When
        TopUp result = insertTopUpCommand.execute(topUp).join();

        // Then
        assertEquals(null, result);
    }
}
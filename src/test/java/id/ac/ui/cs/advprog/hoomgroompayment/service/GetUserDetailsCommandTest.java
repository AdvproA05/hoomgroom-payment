package id.ac.ui.cs.advprog.hoomgroompayment.service;

import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.service.command.GetUserDetailsCommand;
import id.ac.ui.cs.advprog.hoomgroompayment.service.receiver.TopUpReceiverImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetUserDetailsCommandTest {

    @Mock
    private TopUpReceiverImpl topUpReceiver;

    @InjectMocks
    private GetUserDetailsCommand getUserDetailsCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute_Success() {
        // Given
        HttpServletRequest request = null;
        UserDetails userDetails = new UserDetails();
        userDetails.setId(1L);
        userDetails.setUsername("john_doe");
        userDetails.setWalletBalance(100.0);

        when(topUpReceiver.getUserDetails(request)).thenReturn(userDetails);

        // When
        UserDetails result = getUserDetailsCommand.execute(request);

        // Then
        assertEquals(userDetails, result);
    }

    @Test
    public void testExecute_Failure() {
        // Given
        HttpServletRequest request = null;

        when(topUpReceiver.getUserDetails(request)).thenReturn(null);

        // When
        UserDetails result = getUserDetailsCommand.execute(request);

        // Then
        assertEquals(null, result);
    }
}

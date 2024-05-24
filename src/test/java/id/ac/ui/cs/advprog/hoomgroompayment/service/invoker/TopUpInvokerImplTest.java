package id.ac.ui.cs.advprog.hoomgroompayment.service.invoker;

import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.service.command.GetUserDetailsCommand;
import id.ac.ui.cs.advprog.hoomgroompayment.service.command.InsertTopUpCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TopUpInvokerImplTest {

    @Mock
    private GetUserDetailsCommand getUserDetailsCommand;

    @Mock
    private InsertTopUpCommand insertTopUpCommand;

    @Mock
    private HttpServletRequest request;

    @Mock
    private UserDetails userDetails;

    @Mock
    private TopUp topUp;

    private TopUpInvokerImpl topUpInvoker;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        topUpInvoker = new TopUpInvokerImpl(getUserDetailsCommand, insertTopUpCommand);
    }

    @Test
    @DisplayName("Should insert top up")
    public void shouldInsertTopUp() {
        when(insertTopUpCommand.execute(any())).thenReturn(CompletableFuture.completedFuture(topUp));

        topUpInvoker.insertTopUp(topUp);

        verify(insertTopUpCommand, times(1)).execute(any(TopUp.class));
    }
}
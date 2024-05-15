package id.ac.ui.cs.advprog.hoomgroompayment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.hoomgroompayment.enums.TopUpAmount;
import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.service.invoker.TopUpInvokerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class TopUpControllerTest {

    @Mock
    private TopUpInvokerImpl topUpService;

    @InjectMocks
    private TopUpController topUpController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void redirectToTopUp_UserDetailsNotNull() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername("username");
        userDetails.setWalletBalance(100.0);

        when(topUpService.getUserDetails(any(HttpServletRequest.class))).thenReturn(CompletableFuture.completedFuture(userDetails));

        HttpServletRequest request = mock(HttpServletRequest.class);

        CompletableFuture<ResponseEntity<?>> responseEntityFuture = topUpController.redirectToTopUp(request);

        // Menunggu hingga CompletableFuture selesai dan mendapatkan ResponseEntity
        ResponseEntity<?> responseEntity = responseEntityFuture.get();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDetails, responseEntity.getBody());
    }

    @Test
    void redirectToTopUp_UserDetailsNull() throws Exception {
        when(topUpService.getUserDetails(any(HttpServletRequest.class))).thenReturn(CompletableFuture.completedFuture(null));

        HttpServletRequest request = mock(HttpServletRequest.class);

        CompletableFuture<ResponseEntity<?>> responseEntityFuture = topUpController.redirectToTopUp(request);

        // Menunggu hingga CompletableFuture selesai dan mendapatkan ResponseEntity
        ResponseEntity<?> responseEntity = responseEntityFuture.get();

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Unauthorized", responseEntity.getBody());
    }

    @Test
    void insertTopUp_UserDetailsNotNull() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername("username");
        userDetails.setWalletBalance(100.0);

        when(topUpService.getUserDetails(any(HttpServletRequest.class))).thenReturn(CompletableFuture.completedFuture(userDetails));
        when(topUpService.insertTopUp(any(TopUp.class))).thenReturn(CompletableFuture.completedFuture(new TopUp()));

        HttpServletRequest request = mock(HttpServletRequest.class);
        TopUp topUp = new TopUp();
        topUp.setUsername("username");
        topUp.setAmount(TopUpAmount.ONEHUNDREDRUPIAH.getValue());

        CompletableFuture<ResponseEntity<?>> responseEntityFuture = topUpController.insertTopUp(request, topUp);

        // Menunggu hingga CompletableFuture selesai dan mendapatkan ResponseEntity
        ResponseEntity<?> responseEntity = responseEntityFuture.get();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDetails, responseEntity.getBody());
    }

    @Test
    void insertTopUp_UserDetailsNull() throws Exception {
        when(topUpService.getUserDetails(any(HttpServletRequest.class))).thenReturn(CompletableFuture.completedFuture(null));

        HttpServletRequest request = mock(HttpServletRequest.class);
        TopUp topUp = new TopUp();

        CompletableFuture<ResponseEntity<?>> responseEntityFuture = topUpController.insertTopUp(request, topUp);

        // Menunggu hingga CompletableFuture selesai dan mendapatkan ResponseEntity
        ResponseEntity<?> responseEntity = responseEntityFuture.get();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Bad Request", responseEntity.getBody());
    }
}

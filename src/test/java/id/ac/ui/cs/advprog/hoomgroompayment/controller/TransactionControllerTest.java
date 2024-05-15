package id.ac.ui.cs.advprog.hoomgroompayment.controller;

import id.ac.ui.cs.advprog.hoomgroompayment.controller.TransactionController;
import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;
import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.service.invoker.TransactionInvoker;
import id.ac.ui.cs.advprog.hoomgroompayment.service.receiver.TopUpReceiver;
import id.ac.ui.cs.advprog.hoomgroompayment.util.PromoCodeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionInvoker transactionInvoker;

    @Mock
    private TopUpReceiver topUpReceiver;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void purchaseProductWithNoPromoCode() {
        when(topUpReceiver.getUserDetails(any())).thenReturn(CompletableFuture.completedFuture(new UserDetails()));
        when(transactionInvoker.updateUserBalance(any(), anyDouble())).thenReturn(CompletableFuture.completedFuture(new UserDetails()));

        CompletableFuture<ResponseEntity<?>> response = transactionController.purchaseProduct(null, 100.0, null);

        assertEquals(200, response.join().getStatusCodeValue());
        verify(transactionInvoker, times(1)).updateUserBalance(any(), eq(100.0));
    }

    @Test
    public void purchaseProductWithValidPromoCode() {
        PromoCode promoCode = new PromoCode();
        promoCode.setName("PROMO10");
        promoCode.setValidDate(LocalDate.now().plusDays(1));
        promoCode.setMinPurchase(0.0);

        when(topUpReceiver.getUserDetails(any())).thenReturn(CompletableFuture.completedFuture(new UserDetails()));
        when(transactionInvoker.updateUserBalance(any(), anyDouble())).thenReturn(CompletableFuture.completedFuture(new UserDetails()));

        CompletableFuture<ResponseEntity<?>> response = transactionController.purchaseProduct(null, 100.0, promoCode);

        assertEquals(200, response.join().getStatusCodeValue());
        verify(transactionInvoker, times(1)).updateUserBalance(any(), eq(90.0));
    }

    @Test
    public void purchaseProductWithNoUserDetails() {
        when(topUpReceiver.getUserDetails(any())).thenReturn(CompletableFuture.completedFuture(null));

        CompletableFuture<ResponseEntity<?>> response = transactionController.purchaseProduct(null, 100.0, null);

        assertEquals(400, response.join().getStatusCodeValue());
    }
}
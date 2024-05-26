package id.ac.ui.cs.advprog.hoomgroompayment.controller;

import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;
import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import id.ac.ui.cs.advprog.hoomgroompayment.service.TransactionServiceImpl;
import id.ac.ui.cs.advprog.hoomgroompayment.service.invoker.TransactionInvoker;
import id.ac.ui.cs.advprog.hoomgroompayment.service.receiver.TopUpReceiver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionInvoker transactionInvoker;

    @Mock
    private TopUpReceiver topUpReceiver;
    @Mock
    private TransactionServiceImpl transactionService;

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
    @Test
    public void testGetStatistics() {
        // Mock data
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();
        UUID productId3 = UUID.randomUUID();

        List<Map.Entry<UUID, Long>> mockTop10Products = Arrays.asList(
                Map.entry(productId1, 100L),
                Map.entry(productId2, 90L),
                Map.entry(productId3, 80L)
                // Add more mock data as needed
        );

        // Mock service method call
        when(transactionService.calculateTop10Products()).thenReturn(mockTop10Products);

        // Test the controller method
        ResponseEntity<?> responseEntity = transactionController.getStatistics();

        // Assertions
        // Assert that the response entity is OK
        // You can add more assertions as needed
        assertEquals(200, responseEntity.getStatusCodeValue());

        List<Map.Entry<UUID, Long>> top10Products = (List<Map.Entry<UUID, Long>>) responseEntity.getBody();
        // Assert that the returned list contains the expected data
        // You can add more assertions as needed
        assertEquals(3, top10Products.size());
        assertEquals(productId1, top10Products.get(0).getKey());
        assertEquals(100L, top10Products.get(0).getValue());
        assertEquals(productId2, top10Products.get(1).getKey());
        assertEquals(90L, top10Products.get(1).getValue());
        assertEquals(productId3, top10Products.get(2).getKey());
        assertEquals(80L, top10Products.get(2).getValue());
    }
}
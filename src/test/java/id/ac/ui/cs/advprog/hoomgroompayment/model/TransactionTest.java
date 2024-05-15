package id.ac.ui.cs.advprog.hoomgroompayment.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class TransactionTest {

    @Test
    public void testTransactionConstructor() {
        Long userId = 20240501164729929L;
        UUID productId = UUID.randomUUID();
        Integer productAmount = 2;

        Transaction transaction = new Transaction(userId, productId, productAmount);

        assertEquals(userId, transaction.getUserId());
        assertEquals(productId, transaction.getProductId());
        assertEquals(productAmount, transaction.getProductAmount());
        assertNull(transaction.getTransactionId());
        assertNull(transaction.getPaymentDate());
        assertNull(transaction.getTotalPrice());
    }

}


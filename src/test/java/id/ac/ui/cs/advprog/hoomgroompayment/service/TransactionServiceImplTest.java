package id.ac.ui.cs.advprog.hoomgroompayment.service;

import id.ac.ui.cs.advprog.hoomgroompayment.model.Transaction;
import id.ac.ui.cs.advprog.hoomgroompayment.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    public void testFindAllByUserId() {
        Long userId = 20240501164729929L;
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();
        Transaction transaction1 = new Transaction(userId, productId1, 2);
        Transaction transaction2 = new Transaction(userId, productId2, 1);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionRepository.findAllByUserId(userId)).thenReturn(transactions);

        List<Transaction> result = transactionService.findAllByUserId(userId);

        assertEquals(transactions.size(), result.size());
        assertEquals(transactions.get(0).getUserId(), result.get(0).getUserId());
        assertEquals(transactions.get(1).getUserId(), result.get(1).getUserId());
        assertEquals(transactions.get(0).getProductId(), result.get(0).getProductId());
        assertEquals(transactions.get(1).getProductId(), result.get(1).getProductId());
    }
}

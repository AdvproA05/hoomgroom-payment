package id.ac.ui.cs.advprog.hoomgroompayment.repository;

import id.ac.ui.cs.advprog.hoomgroompayment.model.Transaction;
import id.ac.ui.cs.advprog.hoomgroompayment.service.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionRepositoryTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    public void testFindAllByUserId() {
        // Membuat list transaction palsu
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Transaction transaction = new Transaction();
            transaction.setProductId(UUID.randomUUID());
            transaction.setUserId(1L); // Ganti dengan ID pengguna yang sesuai
            transactions.add(transaction);
        }

        // Konfigurasi mock repository
        when(transactionRepository.findAllByUserId(1L)).thenReturn(transactions);

        // Panggil metode yang ingin diuji
        List<Transaction> result = transactionService.findAllByUserId(1L);

        // Memeriksa hasilnya
        assertEquals(transactions.size(), result.size());
    }
}


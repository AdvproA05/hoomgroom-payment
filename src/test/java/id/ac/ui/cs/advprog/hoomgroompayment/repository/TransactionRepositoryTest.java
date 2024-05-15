package id.ac.ui.cs.advprog.hoomgroompayment.repository;

import id.ac.ui.cs.advprog.hoomgroompayment.model.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testFindAllByUserId() {
        Long userId = 20240501164729929L;
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();
        Transaction transaction1 = new Transaction(userId, productId1, 2);
        Transaction transaction2 = new Transaction(userId, productId2, 1);
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

        List<Transaction> transactions = transactionRepository.findAllByUserId(userId);

        assertEquals(2, transactions.size());
        assertEquals(userId, transactions.get(0).getUserId());
        assertEquals(userId, transactions.get(1).getUserId());
        assertTrue(transactions.stream().anyMatch(t -> t.getProductId().equals(productId1)));
        assertTrue(transactions.stream().anyMatch(t -> t.getProductId().equals(productId2)));
    }

}

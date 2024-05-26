package id.ac.ui.cs.advprog.hoomgroompayment.service;
import id.ac.ui.cs.advprog.hoomgroompayment.model.Transaction;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TransactionService {
    List<Transaction> findAllByUserId(Long userId);
    List<Map.Entry<UUID, Long>> calculateTop10Products();
}
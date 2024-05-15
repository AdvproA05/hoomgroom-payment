package id.ac.ui.cs.advprog.hoomgroompayment.service;
import id.ac.ui.cs.advprog.hoomgroompayment.model.Transaction;
import java.util.List;

public interface TransactionService {
    List<Transaction> findAllByUserId(Long userId);
}
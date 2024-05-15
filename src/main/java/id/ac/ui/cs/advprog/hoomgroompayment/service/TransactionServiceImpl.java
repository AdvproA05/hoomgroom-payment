package id.ac.ui.cs.advprog.hoomgroompayment.service;
import id.ac.ui.cs.advprog.hoomgroompayment.model.Transaction;
import id.ac.ui.cs.advprog.hoomgroompayment.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAllByUserId(Long userId){
        return transactionRepository.findAllByUserId(userId);
    }

}
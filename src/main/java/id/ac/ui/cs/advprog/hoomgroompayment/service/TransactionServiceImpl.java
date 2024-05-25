package id.ac.ui.cs.advprog.hoomgroompayment.service;
import id.ac.ui.cs.advprog.hoomgroompayment.model.Transaction;
import id.ac.ui.cs.advprog.hoomgroompayment.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    @Override
    public List<Map.Entry<UUID, Long>> calculateTop10Products() {
        // Logic to calculate the top 10 most sold products
        List<Map.Entry<UUID, Long>> top10Products = transactionRepository.findTop10ProductsBySaleCount();
        return top10Products;
    }
}
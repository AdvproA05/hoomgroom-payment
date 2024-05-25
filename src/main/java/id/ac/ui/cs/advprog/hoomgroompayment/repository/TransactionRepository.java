package id.ac.ui.cs.advprog.hoomgroompayment.repository;

import id.ac.ui.cs.advprog.hoomgroompayment.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findAllByUserId(Long userId);
    @Query("SELECT t.productId, SUM(t.productAmount) as saleCount " +
            "FROM Transaction t " +
            "GROUP BY t.productId " +
            "ORDER BY saleCount DESC")
    List<Map.Entry<UUID, Long>> findTop10ProductsBySaleCount();
}

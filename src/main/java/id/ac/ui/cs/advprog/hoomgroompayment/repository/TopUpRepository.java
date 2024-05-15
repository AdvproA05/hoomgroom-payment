package id.ac.ui.cs.advprog.hoomgroompayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.model.primary.keys.TopUpPrimaryKeys;
import java.util.Date;
import java.util.Optional;

@Repository
public interface TopUpRepository extends JpaRepository<TopUp, TopUpPrimaryKeys> {
    Optional<TopUp> findByTimestampAndUsername(Date timestamp, String username);
}
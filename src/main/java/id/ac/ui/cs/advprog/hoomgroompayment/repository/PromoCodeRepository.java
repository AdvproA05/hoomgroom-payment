package id.ac.ui.cs.advprog.hoomgroompayment.repository;

import java.util.*;

import id.ac.ui.cs.advprog.hoomgroompayment.model.PromoCode;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, UUID> {
    Optional<PromoCode> findByName(String name);
}

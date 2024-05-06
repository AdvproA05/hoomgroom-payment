package id.ac.ui.cs.advprog.hoomgroompayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}

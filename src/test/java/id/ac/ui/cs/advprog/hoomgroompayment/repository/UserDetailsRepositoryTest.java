package id.ac.ui.cs.advprog.hoomgroompayment.repository;

import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Tidak mengganti database
public class UserDetailsRepositoryTest {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Test
    public void testFindByUsername() {
        // Given
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername("john_doe");
        userDetails.setWalletBalance(100.0);
        userDetailsRepository.save(userDetails);

        // When
        UserDetails found = userDetailsRepository.findByUsername("john_doe");

        // Then
        assertNotNull(found);
        assertEquals("john_doe", found.getUsername());
        assertEquals(100.0, found.getWalletBalance());
    }
}


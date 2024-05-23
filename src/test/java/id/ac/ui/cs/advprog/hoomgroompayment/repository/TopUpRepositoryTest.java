package id.ac.ui.cs.advprog.hoomgroompayment.repository;

import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.model.primary.keys.TopUpPrimaryKeys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Tidak mengganti database
class TopUpRepositoryTest {

    @Autowired
    private TopUpRepository topUpRepository;

//    @Test
//    void testFindByTimestampAndUsername() {
//        // Given
//        Date timestamp = new Date();
//        String username = "testUser";
//        double amount = 100000;
//        TopUp topUp = new TopUp(timestamp, username, amount);
//        topUpRepository.save(topUp);
//
//        // When
//        Optional<TopUp> found = topUpRepository.findByTimestampAndUsername(timestamp, username);
//
//        // Then
//        assertTrue(found.isPresent());
//        assertEquals(timestamp, found.get().getTimestamp());
//        assertEquals(username, found.get().getUsername());
//        assertEquals(amount, found.get().getAmount());
//    }
}

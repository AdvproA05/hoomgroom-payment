package id.ac.ui.cs.advprog.hoomgroompayment.repository;

import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TopUpRepositoryTest {

    @Mock
    private TopUpRepository topUpRepository;

    @InjectMocks
    private TopUp topUp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        topUp = new TopUp(new Date(), "testUser", 100000);
    }

    @Test
    void findByTimestampAndUsernameReturnsTopUp() {
        when(topUpRepository.findByTimestampAndUsername(topUp.getTimestamp(), topUp.getUsername()))
                .thenReturn(Optional.of(topUp));

        Optional<TopUp> found = topUpRepository.findByTimestampAndUsername(topUp.getTimestamp(), topUp.getUsername());

        assertTrue(found.isPresent());
        assertEquals(topUp.getTimestamp(), found.get().getTimestamp());
        assertEquals(topUp.getUsername(), found.get().getUsername());
        assertEquals(topUp.getAmount(), found.get().getAmount());
    }

    @Test
    void findByTimestampAndUsernameReturnsEmptyForNonExistentUser() {
        when(topUpRepository.findByTimestampAndUsername(topUp.getTimestamp(), "nonExistentUser"))
                .thenReturn(Optional.empty());

        Optional<TopUp> found = topUpRepository.findByTimestampAndUsername(topUp.getTimestamp(), "nonExistentUser");

        assertTrue(found.isEmpty());
    }
}

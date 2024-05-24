package id.ac.ui.cs.advprog.hoomgroompayment.repository;

import id.ac.ui.cs.advprog.hoomgroompayment.model.UserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsRepositoryTest {

    @Mock
    private UserDetailsRepository userDetailsRepository;

    private UserDetails userDetails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userDetails = new UserDetails();
        userDetails.setUsername("john_doe");
        userDetails.setWalletBalance(100.0);
    }

    @Test
    @DisplayName("Should return UserDetails when username exists")
    public void findByUsernameReturnsUserDetails() {
        when(userDetailsRepository.findByUsername(userDetails.getUsername()))
                .thenReturn(userDetails);

        UserDetails found = userDetailsRepository.findByUsername(userDetails.getUsername());

        assertNotNull(found);
        assertEquals(userDetails.getUsername(), found.getUsername());
        assertEquals(userDetails.getWalletBalance(), found.getWalletBalance());
    }

    @Test
    @DisplayName("Should return null when username does not exist")
    public void findByUsernameReturnsNullForNonExistentUser() {
        when(userDetailsRepository.findByUsername("nonExistentUser"))
                .thenReturn(null);

        UserDetails found = userDetailsRepository.findByUsername("nonExistentUser");

        assertNull(found);
    }
}


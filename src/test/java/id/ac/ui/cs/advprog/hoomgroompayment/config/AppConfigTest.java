package id.ac.ui.cs.advprog.hoomgroompayment.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AppConfigTest {

    private AppConfig appConfig;

    @BeforeEach
    public void setUp() {
        appConfig = new AppConfig();
    }

    @Test
    @DisplayName("Should return correct transaction amount")
    public void transactionAmountReturnsCorrectValue() {
        double expected = 100.0;
        double actual = appConfig.transactionAmount();

        assertEquals(expected, actual);
    }
}

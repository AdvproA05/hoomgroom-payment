package id.ac.ui.cs.advprog.hoomgroompayment.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import jakarta.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CorsConfigTest {

    private CorsConfig corsConfig;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockFilterChain chain;

    @BeforeEach
    void setUp() {
        corsConfig = new CorsConfig();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        chain = new MockFilterChain();
    }

    @Test
    void corsFilterAllowsExpectedOrigins() throws ServletException, IOException {
        request.setMethod("GET");
        request.addHeader("Origin", "http://localhost:3001");

        corsConfig.corsFilter().doFilter(request, response, chain);

        assertEquals("http://localhost:3001", response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test
    void corsFilterRejectsUnexpectedOrigins() throws ServletException, IOException {
        request.setMethod("GET");
        request.addHeader("Origin", "http://unexpected.com");

        corsConfig.corsFilter().doFilter(request, response, chain);

        assertEquals(null, response.getHeader("Access-Control-Allow-Origin"));
    }
}

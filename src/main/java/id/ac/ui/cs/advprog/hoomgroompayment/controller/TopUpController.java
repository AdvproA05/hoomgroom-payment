package id.ac.ui.cs.advprog.hoomgroompayment.controller;

import id.ac.ui.cs.advprog.hoomgroompayment.model.TopUp;
import id.ac.ui.cs.advprog.hoomgroompayment.service.invoker.TopUpInvokerImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/top-up")
public class TopUpController {
    private final TopUpInvokerImpl topUpService;

    public TopUpController(TopUpInvokerImpl topUpService) {
        this.topUpService = topUpService;
    }

    @GetMapping(path = {"", "/"})
    public CompletableFuture<ResponseEntity<?>> redirectToTopUp(HttpServletRequest request) {
        return topUpService.getUserDetails(request)
                .thenApply(userDetails -> {
                    if (userDetails == null) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
                    } else {
                        return ResponseEntity.ok().body(userDetails);
                    }
                });
    }

    @PostMapping(path = {"/insert-topup", "/insert-topup/"})
    public CompletableFuture<ResponseEntity<?>> insertTopUp(HttpServletRequest request,
                                                            @RequestBody TopUp data) {
        return topUpService.getUserDetails(request)
                .thenCompose(userDetails -> {
                    if (userDetails != null) {
                        return topUpService.insertTopUp(data)
                                .thenApply(result -> ResponseEntity.ok().body(userDetails));
                    } else {
                        return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Bad Request"));
                    }
                });
    }
}
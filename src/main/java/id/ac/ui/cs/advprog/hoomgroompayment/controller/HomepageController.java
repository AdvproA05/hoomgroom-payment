package id.ac.ui.cs.advprog.hoomgroompayment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomepageController {

  @GetMapping("/")
  public ResponseEntity<String> homepage() {
    return ResponseEntity.ok("homepage");
  }
}
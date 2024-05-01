package id.ac.ui.cs.advprog.hoomgroompayment.model;

import lombok.Getter;
import jakarta.persistence.*;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "topups-payment")
@Getter @Setter
public class TopUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String topUpMethod;
    private Date timestamp;
    private double amount;
}

package id.ac.ui.cs.advprog.hoomgroompayment.model;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="transaction")
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transactionId;
    private Long userId;
    private UUID productId;
    private Integer productAmount;
    private Date paymentDate;
    private Double totalPrice;

    public Transaction(){}

    public Transaction(
            Long userId,
            UUID productId,
            Integer productAmount) {

        this.userId = userId;
        this.productId = productId;
        this.productAmount = productAmount;
    }
}

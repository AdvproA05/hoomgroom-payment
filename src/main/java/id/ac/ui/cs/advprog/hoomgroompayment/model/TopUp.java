package id.ac.ui.cs.advprog.hoomgroompayment.model;

import id.ac.ui.cs.advprog.hoomgroompayment.enums.TopUpAmount;
import id.ac.ui.cs.advprog.hoomgroompayment.model.primary.keys.TopUpPrimaryKeys;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "topups-payment")
@IdClass(TopUpPrimaryKeys.class)
@NoArgsConstructor
@Getter
@Setter
public class TopUp {
    @Id
    private String username;

    private String topUpMethod = "dummy wallet";

    @Id
    private Date timestamp;

    private double amount;

    public TopUp(Date timestamp, String username, double amount) {
        this.timestamp = timestamp;
        this.username = username;
        setAmount(amount);
    }

    public void setAmount(double amount) {
        if (TopUpAmount.contains(amount)) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("Invalid top-up amount");
        }
    }
}
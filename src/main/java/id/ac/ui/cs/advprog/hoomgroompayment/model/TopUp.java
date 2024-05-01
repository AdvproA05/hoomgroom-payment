package id.ac.ui.cs.advprog.hoomgroompayment.model;

import lombok.Getter;
import jakarta.persistence.*;
import lombok.Setter;

import java.util.Date;
import id.ac.ui.cs.advprog.hoomgroompayment.enums.TopUpAmount;

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

    public TopUp(Date timestamp, String username, double amount) {
        this.timestamp = timestamp;
        this.username = username;
        this.topUpMethod = "dummy wallet";
        setAmount(amount);
    }

    public TopUp() {
        this.topUpMethod = "dummy wallet";
    }

    public void setAmount(double amount) {
        if (TopUpAmount.contains(amount)) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException();
        }
    }
}

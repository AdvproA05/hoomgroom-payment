package id.ac.ui.cs.advprog.hoomgroompayment.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name="promo-codes")
@Getter @Setter
public class PromoCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;
    private LocalDate validDate;
    private Double minPurchase;
}

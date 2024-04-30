package id.ac.ui.cs.advprog.hoomgroompayment.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter
public class PromoCode {
    private UUID id;
    private String name;
    private String description;
    private LocalDate validDate;
    private Double minPurchase;
}

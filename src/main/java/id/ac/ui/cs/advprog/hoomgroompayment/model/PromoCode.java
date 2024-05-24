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
    public UUID id;
    public String name;
    public String description;
    public LocalDate validDate;
    public Double minPurchase;


    public PromoCode(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.validDate = builder.validDate;
        this.minPurchase = builder.minPurchase;
    }

    public PromoCode() {

    }

    public static class Builder {
        private UUID id;
        private String name;
        private String description;
        private LocalDate validDate;
        private Double minPurchase;

        public Builder() {
            this.id = UUID.randomUUID();
        }

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            if (!name.matches("[A-Z0-9]+")) {
                throw new IllegalArgumentException();
            }
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withValidDate(LocalDate validDate) {
            this.validDate = validDate;
            return this;
        }

        public Builder withMinPurchase(Double minPurchase) {
            if (minPurchase <= 0) {
                throw new IllegalArgumentException();
            }
            this.minPurchase = minPurchase;
            return this;
        }

        public PromoCode build() {
            if (name == null || description == null || minPurchase == null) {
                throw new IllegalStateException("Name, description, and minPurchase date are required");
            }
            return new PromoCode(this);
        }
    }
}



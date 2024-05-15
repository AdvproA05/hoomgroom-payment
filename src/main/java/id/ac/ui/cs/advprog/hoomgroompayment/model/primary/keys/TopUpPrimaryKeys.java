package id.ac.ui.cs.advprog.hoomgroompayment.model.primary.keys;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@Embeddable
public class TopUpPrimaryKeys implements Serializable {
    private Date timestamp;
    private String username;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TopUpPrimaryKeys that)) return false;

        return
                Objects.equals(this.getTimestamp(), that.getTimestamp()) && Objects.equals(this.getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getTimestamp(), this.getUsername());
    }
}
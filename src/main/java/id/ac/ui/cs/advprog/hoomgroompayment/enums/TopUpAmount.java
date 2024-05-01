package id.ac.ui.cs.advprog.hoomgroompayment.enums;

import lombok.Getter;

@Getter
public enum TopUpAmount {
    TENRUPIAH(10000.0),
    TWENTYFIVERUPIAH(25000.0),
    FIFTYRUPIAH(50000.0),
    ONEHUNDREDRUPIAH(100000.0);

    private final double value;

    private TopUpAmount(double value) {
        this.value = value;
    }

    public static boolean contains(double param) {
        for (TopUpAmount topUp : TopUpAmount.values()) {
            if (topUp.getValue() == param) {
                return true;
            }
        }

        return false;
    }
}

package id.ac.ui.cs.advprog.hoomgroompayment.util;
public class PromoCodeUtil {
    public static double extractDiscountPercentage(String promoCode) {
        String[] parts = promoCode.split("(?<=\\D)(?=\\d)");
        if (parts.length > 1) {
            return Double.parseDouble(parts[1]);
        } else {
            return 0;
        }
    }
}

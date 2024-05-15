package id.ac.ui.cs.advprog.hoomgroompayment.util;
public class PromoCodeUtil {
    public static double extractDiscountPercentage(String promoCode) {
        String[] parts = promoCode.split("(?<=\\D)(?=\\d)");
        if (parts.length > 1) {
            double discountPercentage = Double.parseDouble(parts[1]);
            return discountPercentage;
        } else {
            return 0;
        }
    }
}

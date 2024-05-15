package id.ac.ui.cs.advprog.hoomgroompayment.util;
public class PromoCodeUtil {
    public static int extractDiscountPercentage(String promoCode) {
        String[] parts = promoCode.split("(?<=\\D)(?=\\d)");
        if (parts.length > 1) {
            int discountPercentage = Integer.parseInt(parts[1]);
            return discountPercentage;
        } else {
            return 0;
        }
    }
}

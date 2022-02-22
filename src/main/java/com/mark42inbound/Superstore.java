package com.mark42inbound;

import java.util.List;

public class Superstore {

    Item A, B, C, D;

    List<PricingRule> transaction1Rules;
    List<PricingRule> transaction2Rules;

    public static void main(String[] args) {
        var test = new Superstore();
        test.init();
        test.doCheckout();
    }

    public void init() {
        A = Item.factory("A", 50d);
        B = Item.factory("B", 30d);
        C = Item.factory("C", 20d);
        D = Item.factory("D", 15d);

        transaction1Rules = List.of(
                PricingRule.withSpecialPrice(A, 3, 130d),
                PricingRule.withSpecialPrice(B, 2, 45d),
                PricingRule.withoutSpecialPrice(C),
                PricingRule.withoutSpecialPrice(D)
        );

        transaction2Rules = List.of(
                PricingRule.withoutSpecialPrice(A),
                PricingRule.withSpecialPrice(B, 3, 50d),
                PricingRule.withSpecialPrice(C, 5, 60d),
                PricingRule.withoutSpecialPrice(D)
        );
    }

    public void doCheckout() {
        Checkout ckTransaction1 = Checkout.with(transaction1Rules);
        ckTransaction1.scan(A);
        ckTransaction1.scan(B);
        var price1 = ckTransaction1.calculateTotal();
        System.out.printf("Total for transaction 1: %s%n", price1);

        Checkout ckTransaction2 = Checkout.with(transaction1Rules);
        ckTransaction2.scan(A);
        ckTransaction2.scan(A);
        var price2 = ckTransaction2.calculateTotal();
        System.out.printf("Total for transaction 2: %s%n", price2);

        Checkout ckTransaction3 = Checkout.with(transaction1Rules);
        ckTransaction3.scan(A);
        ckTransaction3.scan(A);
        ckTransaction3.scan(A);
        var price3 = ckTransaction3.calculateTotal();
        System.out.printf("Total for transaction 3: %s%n", price3);

        // When 8 item A and 5 item B are purchased 6 A's and 4 B's will be at special price and 2 A's and 1 B will be at flat rate.
        Checkout ckTransaction4 = Checkout.with(transaction1Rules);
        //a - 2 * 130 +  2 * 50 = 360
        //b - 2 * 45 + 1 * 30 = 120
        //480
        ckTransaction4.scan(A);
        ckTransaction4.scan(B);
        ckTransaction4.scan(A);
        ckTransaction4.scan(A);
        ckTransaction4.scan(B);
        ckTransaction4.scan(A);
        ckTransaction4.scan(B);
        ckTransaction4.scan(A);
        ckTransaction4.scan(B);
        ckTransaction4.scan(B);
        ckTransaction4.scan(A);
        ckTransaction4.scan(A);
        ckTransaction4.scan(A);
        var price4 = ckTransaction4.calculateTotal();
        System.out.printf("Total for transaction 4: %s%n", price4);

        //Moreover, checkout interface is designed in such a way that multiple pricing rules can be applied for
        //subsequent transactions as well.
        Checkout ckTransaction5 = Checkout.with(transaction2Rules);
        //a-2 b-4 c-6 d-1
        //100 + 80 + 80 + 15 = 275
        ckTransaction5.scan(D);
        ckTransaction5.scan(B);
        ckTransaction5.scan(C);
        ckTransaction5.scan(A);
        ckTransaction5.scan(A);
        ckTransaction5.scan(B);
        ckTransaction5.scan(C);
        ckTransaction5.scan(C);
        ckTransaction5.scan(B);
        ckTransaction5.scan(B);
        ckTransaction5.scan(C);
        ckTransaction5.scan(C);
        ckTransaction5.scan(C);
        var price5 = ckTransaction5.calculateTotal();
        System.out.printf("Total for transaction 5: %s%n", price5);
    }
}
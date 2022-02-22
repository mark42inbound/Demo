package com.test;

import lombok.Getter;

@Getter
public class PricingRule {

    private final Item item;
    private final Integer minQuantity;
    private final Double specialPrice;

    private PricingRule(Item item, Integer minQuantity, Double specialPrice) {
        this.item = item;
        this.minQuantity = minQuantity;
        this.specialPrice = specialPrice;
    }

    public static PricingRule withSpecialPrice(Item item, Integer minQuantity, Double specialPrice) {
        return new PricingRule(item, minQuantity, specialPrice);
    }

    public static PricingRule withoutSpecialPrice(Item item) {
        return new PricingRule(item, 0, 0d);
    }
}

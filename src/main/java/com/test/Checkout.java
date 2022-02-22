package com.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Checkout {

    private final Map<Item, PricingRule> pricingRulesMap;
    private final Map<Item, Integer> cart;

    private Checkout(List<PricingRule> pricingRules) {
        this.pricingRulesMap = pricingRules.stream()
                .collect(Collectors.toMap(PricingRule::getItem, Function.identity()));
        this.cart = new HashMap<>();
    }

    public static Checkout with(List<PricingRule> pricingRules) {
        return new Checkout(pricingRules);
    }

    public void scan(Item item) {
        cart.putIfAbsent(item, 0);
        cart.put(item, cart.get(item) + 1);
    }

    public Double calculateTotal() {
        return cart.entrySet().stream()
                .mapToDouble(entry -> calculateSubTotal(entry.getKey(), entry.getValue(), pricingRulesMap.get(entry.getKey())))
                .sum();
    }

    public Double calculateSubTotal(Item item, Integer quantity, PricingRule rule) {
        if (rule.getMinQuantity() > 0 && quantity >= rule.getMinQuantity()) {
            var specialPriceQty = quantity / rule.getMinQuantity();
            var flatPriceQty = quantity % rule.getMinQuantity();
            return specialPriceQty * rule.getSpecialPrice() + item.getUnitPrice() * flatPriceQty;
        } else {
            return item.getUnitPrice() * quantity;
        }
    }
}

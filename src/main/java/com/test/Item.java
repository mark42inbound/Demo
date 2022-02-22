package com.test;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Item {

    private final String sku;
    private final Double unitPrice;

    private Item(String sku, Double unitPrice) {
        this.sku = sku;
        this.unitPrice = unitPrice;
    }

    public static Item factory(String sku, Double unitPrice) {
        return new Item(sku, unitPrice);
    }

    @Override
    public boolean equals(Object obj) {
        if (Objects.isNull(obj))
            return false;

        if (obj.getClass() != this.getClass())
            return false;

        final Item other = (Item) obj;
        return this.sku.equals(other.getSku());
    }

    @Override
    public int hashCode() {
        final var prime = 31;
        var result = 1;
        result = prime * result + ((sku == null) ? 0 : sku.hashCode());
        return result;
    }
}

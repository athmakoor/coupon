package com.coupon.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.AttributeSet;

public class CartRequest implements Serializable {
    private String txn_id;
    private List<String> fields;
    private UserData user_data;
    private List<CartItem> cart_data;

    public String getTxn_id() {
        return txn_id;
    }

    public void setTxn_id(String txn_id) {
        this.txn_id = txn_id;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public UserData getUser_data() {
        return user_data;
    }

    public void setUser_data(UserData user_data) {
        this.user_data = user_data;
    }

    public List<CartItem> getCart_data() {
        return cart_data;
    }

    public void setCart_data(List<CartItem> cart_data) {
        this.cart_data = cart_data;
    }

    public Double getTotalCartValue() {
        Double value = 0.0;

        if (cart_data != null) {
            for (CartItem item : cart_data) {
                if (item.getAmount() != null && item.getQuantity() != null) {
                    value += item.getAmount() * item.getQuantity();
                }
            }
        }

        return value;
    }

    public List<String> getSkus() {
        List<String> skus = new ArrayList<>();

        for (CartItem item : cart_data) {
            if (item.getSku() != null) {
                skus.add(item.getSku());
            }
        }

        return skus;
    }

    public Map<String, Integer> getSkuQuantityMap() {
        Map<String, Integer> skus = new HashMap<>();
        String sku;

        for (CartItem item : cart_data) {
            sku = item.getSku();

            if (sku != null) {
                if (!skus.containsKey(sku)) {
                    skus.put(sku, 0);
                }

                skus.put(sku, skus.get(sku) + item.getQuantity());
            }
        }

        return skus;
    }

    public Map<String, Double> geCategoryCartPriceMap() {
        Map<String, Double> priceMap = new HashMap<>();
        Double price;
        String category;

        for (CartItem item : cart_data) {
            price = item.getAmount();
            category = item.getCategory();

            if (price != null && category != null) {
                if (!priceMap.containsKey(category)) {
                    priceMap.put(category, 0.0);
                }

                priceMap.put(category, priceMap.get(category) + item.getAmount() * item.getQuantity());
            }
        }

        return priceMap;
    }

    public Map<String, List<CartItem>> geCategoryCartItemMap() {
        Map<String, List<CartItem>> categoryMap = new HashMap<>();
        String category;

        for (CartItem item : cart_data) {
            category = item.getCategory();

            if (category != null) {
                if (!categoryMap.containsKey(category)) {
                    categoryMap.put(category, new ArrayList<CartItem>());
                }

                categoryMap.get(category).add(item);
            }
        }

        return categoryMap;
    }

    public Map<String, Double> gePaymentTypeCartPriceMap() {
        Map<String, Double> priceMap = new HashMap<>();
        Double price;
        String type;

        for (CartItem item : cart_data) {
            price = item.getAmount();
            type = item.getType();

            if (price != null && type != null) {
                if (!priceMap.containsKey(type)) {
                    priceMap.put(type, 0.0);
                }

                priceMap.put(type, priceMap.get(type) + item.getAmount() * item.getQuantity());
            }
        }

        return priceMap;
    }
}

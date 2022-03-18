package thanhha.cart;

import thanhha.product.ProductDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CartObject implements Serializable {
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public Integer getQuantityByItemId(String id) {
        if (this.items == null) {
            return 0;
        }

        for (Map.Entry<String, Integer> item : items.entrySet()) {
            if (id.equals(item.getKey())) {
                return item.getValue();
            }
        }
        return 0;
    }

    public void addItemToCart(String id) {
        if (id == null) {
            return;
        }
        if (id.trim().isEmpty()) {
            return;
        }

        if (this.items == null) {
            this.items = new HashMap<>();
        }

        int quantity = 1;
        if (this.items.containsKey(id)) {
            quantity = this.items.get(id) + 1;
        }
        this.items.put(id, quantity);
    }

    public void removeItemFromCart(String id) {
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(id)) {
            this.items.remove(id);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
}

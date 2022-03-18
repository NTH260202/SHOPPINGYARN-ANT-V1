package thanhha.orderDetail;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDetailDTO implements Serializable {
    private String orderId;
    private String productId;
    private int quantity;
    private String customerId;

    public OrderDetailDTO(String orderId, String productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhha.cart;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class CartDTO implements Serializable{
    private String id;
    private String userId;
    private String productId;
    private String quantity;

    public CartDTO(String id, String userId, String productId, String quantity) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartDTO(String userId, String productId, String quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

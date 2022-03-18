package thanhha.product;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDTO implements Serializable {
    private String id;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private int inStock;

    public ProductDTO() {

    }

    public ProductDTO(String id, String name, String imageUrl, BigDecimal price, int inStock) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.inStock = inStock;
    }

    public ProductDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }
}

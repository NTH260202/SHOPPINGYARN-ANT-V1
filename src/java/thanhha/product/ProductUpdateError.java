package thanhha.product;

import java.io.Serializable;

public class ProductUpdateError implements Serializable {
    private String quantityIsInvalid;

    public ProductUpdateError() {
    }

    public String getQuantityIsInvalid() {
        return quantityIsInvalid;
    }

    public void setQuantityIsInvalid(String quantityIsInvalid) {
        this.quantityIsInvalid = quantityIsInvalid;
    }
}

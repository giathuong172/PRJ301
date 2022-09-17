/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuongng.cart;

import java.io.Serializable;
import thuongng.tblProduct.TblProductDTO;

/**
 *
 * @author Admin
 */
public class CartObjectV2 implements Serializable {
   private TblProductDTO product;
   private Integer quantity;

    public CartObjectV2(TblProductDTO product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public CartObjectV2(Integer quantity) {
        this.quantity = quantity;
    }
    
    public CartObjectV2() {
    }
    public TblProductDTO getProduct() {
        return product;
    }

    public void setProduct(TblProductDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
   
    
}

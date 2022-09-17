/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuongng.tblProduct;

/**
 *
 * @author Admin
 */
public class TblProductDTO {
     private int productID;
     private String productName;
     private int price;
     private String description;

    public TblProductDTO() {
    }

    public TblProductDTO(int productID) {
        this.productID = productID;
    }
    
    public TblProductDTO(int productID, String productName, int price, String description) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.description = description;
    }
    
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     
    
}

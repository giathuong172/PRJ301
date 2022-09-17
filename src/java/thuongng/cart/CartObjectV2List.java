/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuongng.cart;

import java.util.ArrayList;
import java.util.Iterator;
import thuongng.tblProduct.TblProductDTO;

/**
 *
 * @author Admin
 */
public class CartObjectV2List {

    private ArrayList<CartObjectV2> list;
    private int totalMoney;
    public ArrayList<CartObjectV2> getList() {
        return list;
    }

    public void AddBookToCart(int bookID, String name, int price, String description) {
        if (this.list == null) {
            list = new ArrayList<>();
        }
        int quantity = 1;
        boolean existed = false;
        for (CartObjectV2 items : list) {
            if (items.getProduct().getProductID() == bookID) {
                existed = true;
                int value = items.getQuantity() + 1;
                items.setQuantity(value);
            }
        }
        if (existed == false) {
            TblProductDTO book = new TblProductDTO(bookID, name, price, description);
            CartObjectV2 cartObject = new CartObjectV2(book, quantity);
            this.list.add(cartObject);
        }
    }

    public void DeleteBookToCart(int bookID) {
        if (this.list == null) {
            return;
        }
        /* for (CartObjectV2 item : list) {
            if(item.getProduct().getProductID() == bookID){
                this.list.remove(item);
            }
            if(this.list.isEmpty()){
                this.list = null;
            }
        }*/
        Iterator< CartObjectV2> itr = list.iterator();
        while (itr.hasNext()) {
            CartObjectV2 cartObject = itr.next();
            if (cartObject.getProduct().getProductID() == bookID) {
                itr.remove();
            }
            if (this.list.isEmpty()) {
                this.list = null;
            }
        }
    }

    public void DeleteAllBookFromCart() {
        if (this.list == null) {
            return;
        }
        Iterator< CartObjectV2> itr = list.iterator();
        while (itr.hasNext()) {
            CartObjectV2 cartObject = itr.next();
            itr.remove();
            if (this.list.isEmpty()) {
                this.list = null;
            }
        }

    }

    public int getAllPrice() {
        int price = 0;
        for (CartObjectV2 item : list) {
            price = price + item.getProduct().getPrice() * item.getQuantity();
        }
        return price;
    }

    public int getTotalMoney() {
        return getAllPrice();
    }
    
}

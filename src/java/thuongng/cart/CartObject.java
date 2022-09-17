/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuongng.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CartObject implements Serializable {
    private Map<String,Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    public void addBookToCart(String book){
        //1.checking existed items;
        if(this.items == null){
            items = new HashMap<>();
        } 
        //2 check existed book
        int quantity = 1;
        if(this.items.containsKey(book)){
            quantity = this.items.get(book) + 1;
        }
        //3 update items
        this.items.put(book, quantity);
    }
    public void removeBookFromCart(String book){
        //1 check existed items 
        if(this.items == null){
            return;
        }
        //2 check existed item ;
        if(this.items.containsKey(book)){
            this.items.remove(book);
            if(this.items.isEmpty()){
                this.items = null;
            }
        }
    }
}

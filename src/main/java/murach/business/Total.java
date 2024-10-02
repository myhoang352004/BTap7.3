/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package murach.business;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class Total implements Serializable{
    private Item item;
    private int quantity;
    
    public Total(Item item, int quantity){
        this.item=item;
        this.quantity=quantity;
    }
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void upQuantity(){
        quantity++;
    }
    public String finalPrice(){
        double priceValue = item.getPrice()*quantity;
        String formattedPrice = String.format("%.2f", priceValue);
        return formattedPrice;
    }
    
    
}

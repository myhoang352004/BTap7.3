/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package murach.business;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Cart {
    private ArrayList<Total> totals = new ArrayList<>();

    public ArrayList<Total> getTotals() {
        return totals;
    }

    public void setTotals(ArrayList<Total> totals) {
        this.totals = totals;
    }
    
    public void addProduct(Item item)
    {
        String id = item.getId();
        
        boolean found = false;
        for(Total total : this.totals){
            if(total.getItem().getId().equals(id))
            {
                total.upQuantity();
                found=true;
                break;
            }
        }
        if(!found)
            this.totals.add(new Total(item,1));
    }   
    public void removeProduct(String id)
    {
        Total itemtoRemove = null;
        for(Total total : this.totals)
        {
            if(total.getItem().getId().equals(id))
            {
                itemtoRemove=total;
                break;
            }
        }
        if(itemtoRemove!=null)
            this.totals.remove(itemtoRemove);
    }
    public void UpdateProduct(String id, int quantity){
        for(Total total : this.totals)
        {
            if(total.getItem().getId().equals(id))
                total.setQuantity(quantity);
            return;
        }
    }
}

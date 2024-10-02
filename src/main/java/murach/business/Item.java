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
public class Item implements Serializable {
    String id;
    String name;
    double price;
    public Item(){
        id="";
        name="";
        price=0.0;
    }
    public Item(String id, String name, double price)
    {
        this.id=id;
        this.name=name;
        this.price=price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}

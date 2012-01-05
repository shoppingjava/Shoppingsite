/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Music;

import Music.BasketItem;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author tobiastangfelt
 */
@ManagedBean(name="myBasket")
@RequestScoped
public class Basket {
    private String itemMessage;

     public Basket (){
        //Meddelande visas om kundvagnen är yom
         if(Items.getCountItems() == 0){
            itemMessage = "You currently have no items in your cart.";
        }
        else{
            itemMessage = "";
        }
    }
    //Meddelande som visas för användaren
    public String getItemMessage() {
        return itemMessage;
    }

    public void setItemMessage(String itemMessage) {
        this.itemMessage = itemMessage;
    }

   
    //Antal varor i kundvagn
    public int getItems() {
        return Items.getCountItems();
    }

    public static void setItems(int items) {
        Items.setCountItems(items);
    }
    //Lägger till album i kundvagn
    public static void addItem (int recordingId, String artistName, String title, String imageName, Float price, Integer stockCount, Integer quantity){
        BasketItem item = new BasketItem();
        BasketItem basketItem = new BasketItem();
        basketItem.setRecordingId(recordingId);
        basketItem.setArtistName(artistName);
        basketItem.setTitle(title);
        basketItem.setImageName(imageName);
        basketItem.setPrice(price);
        basketItem.setStockCount(stockCount);
        basketItem.setQuantity(quantity);
        Items.addCountItems();
        Items.setItem(item);
    }
    
}

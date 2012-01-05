/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Music;

import java.util.ArrayList;

/**
 *
 * @author tobiastangfelt
 */
public class Items {
    static ArrayList<BasketItem> item = new ArrayList<BasketItem>();
    static Integer countItems = 0;

    public static Integer getCountItems() {
        return countItems;
    }

    public static void setCountItems(Integer countItems) {
        Items.countItems = countItems;
    }
    
    public static void addCountItems (){
        countItems++;
    }
    
    static void setItem (BasketItem myBasket){
        item.add(myBasket);
    }
    
   
}

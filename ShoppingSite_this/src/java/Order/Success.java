/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;

import Music.Items;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author tobiastangfelt
 */
@ManagedBean(name="mySuccess")
@RequestScoped
public class Success {

    /** Creates a new instance of Success */
    public Success() {
    }
    //Körs om en betalning lyckats, skriver ut meddelande och rensar kundvagn
    public String getSuccess (){
        Items.deleteItems();
        return "Your order has now been confirmed. An order confirmation will be sent to your e-mail address!";
    }
}

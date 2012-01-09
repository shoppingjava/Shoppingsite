/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Music;

import java.io.IOException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "myItems")
@RequestScoped
public class Items {

    static ArrayList<BasketItem> item = new ArrayList<BasketItem>();
    static Integer countItems = 0;
    DataModel basketItems;
    BasketItem myBasket;
    Integer quantity;

    public static Integer getCountItems() {
        return countItems;
    }

    public static void setCountItems(Integer countItems) {
        Items.countItems = countItems;
    }
    //Räknar upp antalet varor
    public static void addCountItems() {
        countItems++;
    }
    //Körs när användaren väljer att ta bort en vara
    public static void deleteCountItems() {
        countItems--;
    }
    //Lägger till vara i kundkorg
    static void setItem(BasketItem myBasket) {
        item.add(myBasket);
    }
    //Hämtar samtliga varor
    public DataModel getBasket() {
        basketItems = new ListDataModel(item);
        return basketItems;
    }
    //Beräknar den totala summan på samtliga varor
    public float getTotalSum() {
        float sum = 0;
        for (int i = 0; i < item.size(); i++) {
            sum = sum + item.get(i).getSumQuantity();
        }
        return sum;
    }
    //Laddar om sidan
    private void reloadBasket() throws IOException {
        HttpSession ses = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String url = "basket.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }
    //Tar bort specifik vara
    public void deleteItem() throws IOException {
        myBasket = (BasketItem) getBasket().getRowData();
        int i = item.indexOf(myBasket);
        item.remove(i);
        deleteCountItems();
        reloadBasket();
    }
    //Ändrar antalet för en specifik vara   
    public void chnQantity(AjaxBehaviorEvent event, int id, int q) throws IOException {
        for (int i = 0; i < item.size(); i++) {
            if (item.get(i).getRecordingId() == id) {
                item.get(i).setSumQuantity(item.get(i).getPrice() * q);
            }
        }
        reloadBasket();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;

import HibernateShopping.ShoppingSiteHelper;
import HibernateShopping.Users;
import Login.LoginBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tobiastangfelt
 */
@ManagedBean(name = "myOrder")
@RequestScoped
public class Order {

    public static String userValue = "";
    ShoppingSiteHelper helper;

    /** Creates a new instance of Order */
    public Order() {
    }

    public void yourOrder() throws IOException {
        //Anropar sidan om en användare är inloggad
        if (LoginBean.auth == true) {
            getUserInfo();
            HttpSession ses = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            String url = "order.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            //Visar en alertruta om användaren ej är inloggad
        } else {
            userValue = "true";
        }

    }

    public void paymentLink() throws IOException {
        HttpSession ses = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String url = "payment.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }

    public void confirmLink() throws IOException {
        HttpSession ses = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String url = "confirmation.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }
    //Hämtar information om den inloggade användaren för att använda det i beställningsformulär
    public void getUserInfo() {
        helper = new ShoppingSiteHelper();
        List<Users> list = new ArrayList();
        //Hämtar användar data från tabell
        list = helper.getUserData(Login.LoginBean.userName);
        //Lagrar data
        ShippingInfo.setCity(list.get(0).getCity());
        ShippingInfo.setCountry(list.get(0).getCountry());
        ShippingInfo.setEmail(list.get(0).getEmail());
        ShippingInfo.setFirstname(list.get(0).getFirstname());
        ShippingInfo.setLastname(list.get(0).getLastname());
        ShippingInfo.setStreetaddress(list.get(0).getStreetaddress());
        ShippingInfo.setUserId(list.get(0).getUserId());
        ShippingInfo.setUserName(list.get(0).getUserName());
        ShippingInfo.setZip(list.get(0).getZip());
    }

    public String getUserValue() {
        return userValue;
    }

    public void setUserValue(String s) {
        userValue = s;
    }

    public String getCity() {
        return ShippingInfo.getCity();
    }

    public void setCity(String city) {
        ShippingInfo.setCity(city);
    }

    public String getCountry() {
        return ShippingInfo.getCountry();
    }

    public void setCountry(String country) {
        ShippingInfo.setCountry(country);
    }

    public String getEmail() {
        return ShippingInfo.getEmail();
    }

    public void setEmail(String email) {
        ShippingInfo.setEmail(email);
    }

    public String getFirstname() {
        return ShippingInfo.getFirstname();
    }

    public void setFirstname(String firstname) {
        ShippingInfo.setFirstname(firstname);
    }

    public String getLastname() {
        return ShippingInfo.getLastname();
    }

    public void setLastname(String lastname) {
        ShippingInfo.setLastname(lastname);
    }

    public String getStreetaddress() {
        return ShippingInfo.getStreetaddress();
    }

    public void setStreetaddress(String streetaddress) {
        ShippingInfo.setStreetaddress(streetaddress);
    }

    public String getZip() {
        return ShippingInfo.getZip();
    }

    public void setZip(String zip) {
        ShippingInfo.setZip(zip);
    }
}

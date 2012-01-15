/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;

import Music.Items;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tobiastangfelt
 */
@ManagedBean(name = "myConfirm")
@RequestScoped
public class Confirmation {

    private String payment;
    private String paymentPict;
    URL url;

    /** Creates a new instance of Confirmation */
    public Confirmation() {
        payment = getPayment();
    }
    //Hämtar betalningsmetod användaren angivit
    public final String getPayment() {
        return ShippingInfo.getPayment();
    }

    public void setPayment(String payment) {
        ShippingInfo.setPayment(payment);
    }
    //Anger vilken bild som skall visas beroende på vald betalningsmetod
    public String getPayPict() {
        if (payment.equals("PayPal")) {
            return "http://mamanyc.net/wp-content/uploads/2011/11/paypal-logo.jpg";
        } else if (payment.equals("VISA")) {
            return "http://boldprepaid.com/blog/wp-content/uploads/2011/03/visa_logo1.jpg";
        } else {
            return "http://financial-report.info/wp-content/uploads/2010/11/MasterCard_logo.jpg";
        }
    }
    //Anropas när användaren har bekräftat sin beställning
    public void confirmOrder() throws MalformedURLException, IOException {
        if (payment.equals("PayPal")) {
            String token = "";
            String inputLine = "";
            //PayPal länk för att hämta nyckel
            url = new URL("https://api-3t.sandbox.paypal.com/nvp?USER=zecute_1326294244_biz_api1.me.com&PWD=1326294270&SIGNATURE=AiPC9BjkCyDFQXbSkoZcgqH3hpacAOdt03Z2UZgTkXHdePYyEBPMM0-T&VERSION=78&AMT=" + Items.getTotalSumPay() + "&cancelUrl=http://localhost:8080/ShoppingSite_this/faces/confirmation.xhtml&returnUrl=http://localhost:8080/ShoppingSite_this/faces/successful.xhtml&METHOD=SetExpressCheckout");   
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    yc.getInputStream()));
            //Hämtar ut specifik nyckel för att kunna komma åt test PayPal konto (SandBox)
            while ((inputLine = in.readLine()) != null) {
                if (!"".equals(inputLine)) {
                    token = inputLine.substring(6, 28);
                }
            }
            in.close();
            HttpSession ses = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            //Använder nyckeln för att kunna logga in mot PayPal
            String url = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=" + token + "&useraction=commit";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        //Körs om någon annan betalningsmetod än PayPal angivits
        }else {
            HttpSession ses = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            String url = "successful.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
    }
}

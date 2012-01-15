/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;

/**
 *
 * @author tobiastangfelt
 */
public class ShippingInfo {
    public static Integer userId;
    public static String userName;
    public static String userPass;
    public static String firstname;
    public static String lastname;
    public static String streetaddress;
    public static String zip;
    public static String city;
    public static String country;
    public static String email;
    public static String payment = "PayPal";

    public static String getPayment() {
        return payment;
    }

    public static void setPayment(String payment) {
        ShippingInfo.payment = payment;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String c) {
        city = c;
    }

    public static String getCountry() {
        return country;
    }

    public static void setCountry(String c) {
       country = c;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String e) {
        email = e;
    }

    public static String getFirstname() {
        return firstname;
    }

    public static void setFirstname(String f) {
        firstname = f;
    }

    public static String getLastname() {
        return lastname;
    }

    public static void setLastname(String l) {
       lastname = l;
    }

    public static String getStreetaddress() {
        return streetaddress;
    }

    public static void setStreetaddress(String s) {
        streetaddress = s;
    }

    public static Integer getUserId() {
        return userId;
    }

    public static void setUserId(Integer u) {
        userId = u;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String u) {
        userName = u;
    }

    public static String getUserPass() {
        return userPass;
    }

    public static void setUserPass(String u) {
        userPass = u;
    }

    public static String getZip() {
        return zip;
    }

    public static void setZip(String z) {
        zip = z;
    }
}

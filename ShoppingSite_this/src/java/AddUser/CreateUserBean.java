/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AddUser;

import HibernateShopping.HibernateUtil;
import HibernateShopping.Users;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name="createUser")
@SessionScoped
public class CreateUserBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    private String userName;
    //<editor-fold defaultstate="collapsed" desc="Get/Set String userName">
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }
    //</editor-fold>
    
    private String password;
    //<editor-fold defaultstate="collapsed" desc="Get/Set String password">
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    //</editor-fold>
    
    private String firstName;
    //<editor-fold defaultstate="collapsed" desc="Get/Set String firstName">
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }
    //</editor-fold>
    
    private String lastName;
    //<editor-fold defaultstate="collapsed" desc="Get/Set String lastName">
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    //</editor-fold>
    
    private String streetAddress;
    //<editor-fold defaultstate="collapsed" desc="Get/Set String streetAddress">
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    //</editor-fold>
    
    private String zipCode;
    //<editor-fold defaultstate="collapsed" desc="Get/Set String zipCode">
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getZipCode() {
        return zipCode;
    }
    //</editor-fold>
    
    private String city;
    //<editor-fold defaultstate="collapsed" desc="Get/Set String city">
    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }
    //</editor-fold>
    
    private String country;
    //<editor-fold defaultstate="collapsed" desc="Get/Set String country">
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }
    //</editor-fold>
    
    private String email;
    //<editor-fold defaultstate="collapsed" desc="Get/Set String email">
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    //</editor-fold>
    
    private String password1;
    //<editor-fold defaultstate="collapsed" desc="Get/Set String password1">
    public void setPassword1(String password1) {
        this.password1 = password1;
    }
    public String getPassword1() {
        return password1;
    }
    //</editor-fold>
    
    private String password2;
    //<editor-fold defaultstate="collapsed" desc="Get/Set String password2">
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    public String getPassword2() {
        return password2;
    }
    //</editor-fold>
    //</editor-fold>

    
    public String createUserAction() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage;
        //** Kollar om lösenorden är lika **//
        if ( !password1.equals(password2) )
            return null; // Om inte så avslutas metoden här
        else
            setPassword( password1 ); // Om allt är ok
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            
            tx = session.beginTransaction();
            
            // Kollar om användarnamnet redan finns i DB
            Query q = session.createQuery ("from Users WHERE user_name = '"+userName+"'");
            
            if ( !q.list().isEmpty()) {
                facesMessage = new FacesMessage("Account creation failed!");
                facesContext.addMessage("statusForm:statusText", facesMessage);
                return null;
            }
            

            // Är allt lungt så uppdateras DB med ny användare
            Users newuser = new Users();
            newuser.setFirstname(firstName);
            newuser.setLastname(lastName);
            newuser.setStreetaddress(streetAddress);
            newuser.setEmail(email);
            newuser.setZip(zipCode);
            newuser.setCity(city);
            newuser.setCountry(country);
            newuser.setUserName(userName);
            newuser.setUserPass(password);
            
            session.save(newuser);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            facesMessage = new FacesMessage("Account creation failed!");
            facesContext.addMessage("statusForm:statusText", facesMessage);
            return null;
        }

        facesMessage = new FacesMessage("Account successfully created!");
        facesContext.addMessage("statusForm:statusText", facesMessage);
        return null;
    }
    
    public void validateName(FacesContext fc, UIComponent c, Object value) {
        if (((String) value).isEmpty() )
            throw new ValidatorException(
	         new FacesMessage("Enter a valid name"));
   }
    
    public void validateEmail(FacesContext fc, UIComponent c, Object value) {
        // Skapar ett pattern för en vanlig mail address
        // abc@abc.abc
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
        Matcher matcher = pattern.matcher((String) value);
        
        if ( !matcher.matches() )
	      throw new ValidatorException(
	         new FacesMessage("Invalid email"));
   }
    
    /** Kollar om användarnamnet redan existerar i databasen **/
    public void validateUserName(FacesContext fc, UIComponent c, Object value) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        boolean exists = false;
        
        try {
            
            tx = session.beginTransaction();
            
            // Kollar om användarnamnet redan finns i DB
            Query q = session.createQuery ("from Users WHERE user_name = '"+((String) value)+"'");
            
            if ( !q.list().isEmpty())
                exists = true;

        } catch (Exception e) { 
            throw new ValidatorException( new FacesMessage( "Exception: " + e ));
        }
        
        if ( exists )
                throw new ValidatorException(
	         new FacesMessage("Username already exists"));
         
   }
    
    public void validatePassword1(FacesContext fc, UIComponent c, Object value) {
	   if ( ((String) value).equals(" "))
               throw new ValidatorException(
	         new FacesMessage("Password cannot be empty"));
   }
    
    public void validatePassword2(FacesContext fc, UIComponent c, Object value) {
	   if ( !((String) value).equals(password1) )
	      throw new ValidatorException(
	         new FacesMessage("Passwords does not match"));
           if ( ((String) value).isEmpty())
               throw new ValidatorException(
	         new FacesMessage("Password cannot be empty"));
   }
}
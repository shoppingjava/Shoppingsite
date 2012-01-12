/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import HibernateShopping.HibernateUtil;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name="login")
@SessionScoped
public class LoginBean implements Serializable {
    
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
    
    // False om användaren inte är inloggad
    private boolean auth = false;
    //<editor-fold defaultstate="collapsed" desc="Get/Set boolean auth">
    public void setAuth(boolean auth) {
        this.auth = auth;
    }
    public boolean isAuth() {
        return auth;
    }
    //</editor-fold>
    
    public String logIn() {
        
        // Kollar om inmatat username och password finns i databasen
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            
            tx = session.beginTransaction();
            Query q = session.createQuery ("from Users WHERE user_name = '"+userName+"' "
                                                    + "AND user_pass = '"+password+"'");
            
            if ( q.list().isEmpty()) { // Om username inte finns eller om password ej stämmer
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Couldn't log in, check username and password");
                facesContext.addMessage("statusForm:statusText", facesMessage);
                setUserName("");
                setPassword("");
                setAuth(false);
                return null;
            } else { // Om användaren finns i DB
                setAuth(true);
                return null;
            }

        } catch (Exception e) { 
            
            System.out.println(e);
            return e.getMessage();
        }
    }
    
    /** Loggar ut användaren **/
    public String logOut() {
        setAuth(false);
        setUserName("");
        setPassword("");
        return null;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateShopping;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author tobiastangfelt
 */
public class ShoppingSiteHelper {
    Session session = null;

    public ShoppingSiteHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    //Hämtar album beroende på inmatad kategori
    public List getMusicRecordings(String rec){
    List<MusicCategories> musicList = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from MusicRecordings WHERE category = '"+rec+"'");
        musicList = (List<MusicCategories>) q.list();
        

    } catch (Exception e) {
        session.close();
    }

    return musicList;
}
    
   public List getTracks(Integer recordId){
    List<MusicCategories> musicList = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from MusicTracks WHERE recording_id = "+recordId);
        musicList = (List<MusicCategories>) q.list();
        

    } catch (Exception e) {
        session.close();
    }

    return musicList;
}
    
}

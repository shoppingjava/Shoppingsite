/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Music;

import HibernateShopping.MusicRecordings;
import HibernateShopping.ShoppingSiteHelper;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author tobiastangfelt
 */
@ManagedBean(name="myMusic")
@RequestScoped
public class MusicController {
    ShoppingSiteHelper helper;
    MusicRecordings music;
    List<MusicRecordings> musicList = null;
    DataModel musicTitles;
    String category = "Jazz";
    /** Creates a new instance of MusicController */
    public MusicController() {
      helper = new ShoppingSiteHelper();
      musicList = helper.getMusicRecordings(category); 
    }
    //Sätter musikkategori beroende på vilken kategori användaren väljer i leftContent
    public void setMusicLink (String category){
        this.category = category;
    }
    //Musikkategori som visas i centercontent  
    public String getMusicCategory (){
        return this.category;
    }
    //Hämtar album efter specifik kategori
    public DataModel getMusic (){
        
        if(musicTitles == null){
           musicTitles = new ListDataModel(helper.getMusicRecordings(category)); 
       } 
       return musicTitles;
    }
}

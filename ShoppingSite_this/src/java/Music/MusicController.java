/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Music;

import HibernateShopping.MusicRecordings;
import HibernateShopping.ShoppingSiteHelper;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

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
    static String category = "Jazz";
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
    //Sätter musikkategori
    public static void setCategorie (String cat){
        category = cat;
    } 
    //Körs för att få mer info om ett album
    public void setInfoAlbum () throws IOException{
        //Hämtar valt album
        music = (MusicRecordings) getMusic().getRowData();
        Album.setArtistName(music.getArtistName());
        Album.setCategory(music.getCategory());
        Album.setImageName(music.getImageName());
        Album.setPrice(music.getPrice());
        Album.setStockCount(music.getStockCount());
        Album.setTitle(music.getTitle());
        Album.setRecordingId(music.getRecordingId());
        Album.setNumTracks(music.getNumTracks());
        HttpSession ses = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);   
        String url = "album.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }
}

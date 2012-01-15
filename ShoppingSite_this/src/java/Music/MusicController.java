/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Music;

import HibernateShopping.MusicRecordings;
import HibernateShopping.MusicTracks;
import HibernateShopping.ShoppingSiteHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.render.ResponseStateManager;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tobiastangfelt
 */
@ManagedBean(name="myMusic")
@RequestScoped
public class MusicController {
    boolean search = false;
    String searchString = "Type your search here";
    ShoppingSiteHelper helper;
    MusicRecordings music;
    List<MusicRecordings> musicList = null;
    DataModel musicTitles;
    DataModel searchTitles;
    static String category = "Jazz";
    /** Creates a new instance of MusicController */
    public MusicController() {
      helper = new ShoppingSiteHelper();
      musicList = helper.getMusicRecordings(category); 
      ResponseStateManager rsm = FacesContext.getCurrentInstance().getRenderKit().getResponseStateManager();
      
    }
    //Sätter musikkategori beroende på vilken kategori användaren väljer i leftContent
    public void setMusicLink (String category){
        Order.Order.userValue = "";
        this.category = category;
    }
    //Musikkategori som visas i centercontent  
    public String getMusicCategory (){
        return this.category;
    }
    //Hämtar album efter specifik kategori
    public DataModel getMusic (){
      if(search){
         
          return searchTitles;
      }
      else{
        if(musicTitles == null){
           musicTitles = new ListDataModel(helper.getMusicRecordings(category)); 
       } 
       return musicTitles;
      }
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
        Order.Order.userValue = "";
        HttpSession ses = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);   
        String url = "album.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }
    //Lägger till ett album
    public void addAlbum (){
        music = (MusicRecordings) getMusic().getRowData();
        Basket.addItem(music.getRecordingId(), music.getArtistName(), music.getTitle(), music.getImageName(), music.getPrice(), music.getPrice(), music.getStockCount(), 1); 
    }
    //Körs när användaren söker efter album
    public void searchMusic () throws IOException{
        Order.Order.userValue = "";
        //Laddar om index sidan för att sökning skall kunna genomföras från samtliga html sidor
        HttpSession ses = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);   
        String url = "index.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        //Söker genom samtliga tabeller för att se om sökt data finns där
        if(searchTitles == null){

          if(!helper.getMusicRecordings(searchString).isEmpty()){
              List<MusicRecordings> list = new ArrayList();
              list = helper.getMusicRecordings(searchString);
              setMusicLink(list.get(0).getCategory());
              searchTitles = new ListDataModel(helper.getMusicRecordings(searchString));   
              search = true;
          }
          else if(!helper.getMusicRecordingsArtist(searchString).isEmpty()){
              List<MusicRecordings> list = new ArrayList();
              list = helper.getMusicRecordingsArtist(searchString);
              setMusicLink(list.get(0).getCategory());
              searchTitles = new ListDataModel(helper.getMusicRecordingsArtist(searchString));
              search = true;
          }
          else if(!helper.getMusicRecordingsTitle(searchString).isEmpty()){
              List<MusicRecordings> list = new ArrayList();
              list = helper.getMusicRecordingsTitle(searchString);
              setMusicLink(list.get(0).getCategory());
              searchTitles = new ListDataModel(helper.getMusicRecordingsTitle(searchString));
              search = true;
        }
          else if(!helper.getTracksTitle(searchString).isEmpty()){
              DataModel recTitles;
              List<MusicTracks> list = new ArrayList(); 
              list = helper.getTracksTitle(searchString);
              searchTitles = new ListDataModel(helper.getMusicRecordingsId(list.get(0).getRecordingId()));       
              List<MusicRecordings> list1 = new ArrayList();
              list1 = helper.getMusicRecordingsId(list.get(0).getRecordingId());
              setMusicLink(list1.get(0).getCategory());
              search = true;
       }
          //Körs om data ej har hittats
          else{
              searchString = "No Search Results Found";
              setMusicLink("No Search Results Found");
              searchTitles = new ListDataModel(helper.getMusicRecordingsTitle(""));
              search = true;
          }
         
    }
        
    }
    //Kundvagn
    public void linkBasket () throws IOException{
        HttpSession ses = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);   
        String url = "basket.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}

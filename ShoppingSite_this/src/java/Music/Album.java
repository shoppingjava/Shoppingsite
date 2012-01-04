/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Music;

import HibernateShopping.MusicTracks;
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
@ManagedBean(name="myAlbum")
@RequestScoped
public class Album {
     //Album data
     static int recordingId;
     static String artistName;
     static String title;
     static String category;
     static String imageName;
     static Integer numTracks;
     static Float price;
     static Integer stockCount;
    
    ShoppingSiteHelper helper;
    MusicTracks music;
    List<MusicTracks> musicList = null;
    DataModel musicTracks;
    //Antal spår på en CD
    int numberTrack = 0;
    public Album() {
       helper = new ShoppingSiteHelper();
       musicList = helper.getTracks(getRecordingId()); 
    }
    
    public DataModel getTracks (){
        
        if(musicTracks == null){
           musicTracks = new ListDataModel(helper.getTracks(getRecordingId())); 
       } 
       return musicTracks;
    }
    
    public int getNumberTrack (){
        numberTrack++;
        return this.numberTrack;
    }
    
    static void setRecordingId (int myRecordingId){
        recordingId = myRecordingId;
        
    }
    
    public final Integer getRecordingId (){
        return recordingId;
        
    }
    
    static void setStockCount (Integer myStockCount){
        stockCount = myStockCount;
    }
    
    public static void setArtistName (String myArtist){
        artistName = myArtist;
    }
    
    public String getArtistName (){
        return artistName;
    }
    
    static void setTitle (String myTitle){
        title = myTitle;
    }
    
    public String getTitle (){
        return title;
    }
    
    static void setCategory (String myCategory){
        category = myCategory;
    }
    
    public String getCategory (){
        return category;
    }
    
    static void setImageName (String myImageName){
        imageName = myImageName;
    }
    
    public String getImageName (){
       return imageName;
    }
    
    static void setPrice (Float myPrice){
        price = myPrice;
    }
    
    public Float getPrice (){
        return price;
    }
    
    static void setNumTracks (Integer myNumTracks){
        numTracks = myNumTracks;
    }
    
    public Integer getNumTracks (){
        return numTracks;
    }
    //Om en skiva finns i lager eller ej
    public String getStockValue (){
        if(stockCount == 0){
            return "resources/_img/noStockTagg.png";
        }
        else{
            return "resources/_img/yesStockTagg.png";
        }
    }
    //Körs om användaren väljer kategori. Sätter kategori och visar index sidan
    public void toIndex (String myCategory) throws IOException{
        MusicController.setCategorie(myCategory);
        HttpSession ses = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);   
        String url = "index.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }
}

package HibernateShopping;
// Generated 2012-jan-03 13:52:28 by Hibernate Tools 3.2.1.GA



/**
 * MusicRecordings generated by hbm2java
 */
public class MusicRecordings  implements java.io.Serializable {


     private int recordingId;
     private String artistName;
     private String title;
     private String category;
     private String imageName;
     private Integer numTracks;
     private Float price;
     private Integer stockCount;

    public MusicRecordings() {
    }

	
    public MusicRecordings(int recordingId) {
        this.recordingId = recordingId;
    }
    public MusicRecordings(int recordingId, String artistName, String title, String category, String imageName, Integer numTracks, Float price, Integer stockCount) {
       this.recordingId = recordingId;
       this.artistName = artistName;
       this.title = title;
       this.category = category;
       this.imageName = imageName;
       this.numTracks = numTracks;
       this.price = price;
       this.stockCount = stockCount;
    }
   
    public int getRecordingId() {
        return this.recordingId;
    }
    
    public void setRecordingId(int recordingId) {
        this.recordingId = recordingId;
    }
    public String getArtistName() {
        return this.artistName;
    }
    
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    public String getImageName() {
        return this.imageName;
    }
    
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public Integer getNumTracks() {
        return this.numTracks;
    }
    
    public void setNumTracks(Integer numTracks) {
        this.numTracks = numTracks;
    }
    public Float getPrice() {
        return this.price;
    }
    
    public void setPrice(Float price) {
        this.price = price;
    }
    public Integer getStockCount() {
        return this.stockCount;
    }
    
    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }




}



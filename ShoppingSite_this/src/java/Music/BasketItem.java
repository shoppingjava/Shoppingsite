/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Music;

//Vara i kundvagn
public class BasketItem {
    private int recordingId;
    private String artistName;
    private String title;
    private String imageName;
    private Float price;
    private Integer stockCount;
    private Integer quantity;
    private Float sumQuantity;

    public Float getSumQuantity() {
        return sumQuantity;
    }

    public void setSumQuantity(Float sumQuantity) {
        this.sumQuantity = sumQuantity;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getRecordingId() {
        return recordingId;
    }

    public void setRecordingId(int recordingId) {
        this.recordingId = recordingId;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
    
}

package HibernateShopping;
// Generated 2012-jan-03 13:52:28 by Hibernate Tools 3.2.1.GA



/**
 * MusicCategories generated by hbm2java
 */
public class MusicCategories  implements java.io.Serializable {


     private int id;
     private String name;

    public MusicCategories() {
    }

	
    public MusicCategories(int id) {
        this.id = id;
    }
    public MusicCategories(int id, String name) {
       this.id = id;
       this.name = name;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }




}



package springbooturlshortenerservice.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "urls")
public class URL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idURLs")
    private Integer id;

    @Column(name = "longURL")
    private String longUrl;



    @Column(name = "shortID", unique = true)
    private String shortID;

    // Constructors, getters, and setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortID() {
        return shortID;
    }

    public void setShortID(String shortID) {
        this.shortID = shortID;
    }

    @Override
    public String toString() {
        return "URL{" +
                "longUrl='" + longUrl + '\'' +
                ", shortID='" + shortID + '\'' +
                '}';
    }
}


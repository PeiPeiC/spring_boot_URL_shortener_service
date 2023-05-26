package springbooturlshortenerservice.controller;

import javax.persistence.*;


@Entity
@Table(name = "urls")
public class LongUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idURLs")
    private Integer id;

    @Column(name = "longURL")
    private String longUrl;


    @Column(name = "shortURL")
    private String shortUrl;

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

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}


package springbooturlshortenerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbooturlshortenerservice.dao.URLRepository;
import springbooturlshortenerservice.dao.URL;
import org.hashids.Hashids;

@Service
public class URLService {
    private final URLRepository urlRepository;

    @Autowired
    public URLService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String getOrCreateShortURL(String longUrl) {
        URL url = urlRepository.findByLongUrl(longUrl);

        if (url != null) {
            return url.getShortUrl();
        } else {
            String shortUrl = generateShortUrl();
            url = new URL();
            url.setLongUrl(longUrl);
            url.setShortUrl(shortUrl);
            urlRepository.save(url);
            return shortUrl;
        }
    }

    private String generateShortUrl() {
        String baseShortURL = "http://localhost:8080/";
        String shortCode = generateShortCode();
        return baseShortURL + shortCode;
    }

    private String generateShortCode() {
        final String SALT = "my-salt-string";
        final int MIN_LENGTH = 8;
        Hashids hashids = new Hashids(SALT, MIN_LENGTH);
        long now = System.currentTimeMillis();
        return hashids.encode(now);
        }
}


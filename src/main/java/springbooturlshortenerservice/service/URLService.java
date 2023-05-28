package springbooturlshortenerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbooturlshortenerservice.dao.URLRepository;
import springbooturlshortenerservice.dao.URL;
import org.hashids.Hashids;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Service
public class URLService {
    private final URLRepository urlRepository;
    private final String shortCode = this.generateShortCode();;

    @Autowired
    public URLService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String getOrCreateShortURL(String longUrl) {
        URL url = urlRepository.findByLongUrl(longUrl);

        if (url != null) {
            return url.getShortID();
        } else {
            String shortUrl = generateShortUrl();
            url = new URL();
            url.setLongUrl(longUrl);

            url.setShortID(shortCode);
            urlRepository.save(url);
            return shortUrl;
        }
    }

    private String generateShortUrl() {
        // Get the current request URI
        String currentUri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        // Get the current port number
        int port = ServletUriComponentsBuilder.fromCurrentRequest().build().getPort();
        // Generate the current port number URL
        String baseShortURL = ServletUriComponentsBuilder.fromUriString(currentUri).port(port).build().toUriString();

        //String baseShortURL = "http://localhost:8080/";
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


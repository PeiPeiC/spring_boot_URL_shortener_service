package springbooturlshortenerservice.service;

import org.hashids.Hashids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springbooturlshortenerservice.dao.URL;
import springbooturlshortenerservice.dao.URLRepository;

import java.util.Optional;


@Service
public class URLService {

    private static final Logger LOG = LoggerFactory.getLogger(URLService.class);
    private final URLRepository urlRepository;
    private static final String prefix = "http://localhost:";

    @Autowired
    private Environment env;

    @Autowired
    public URLService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String getOrCreateShortURL(String longUrl) {
        URL url = urlRepository.findByLongUrl(longUrl);

        if (url != null) {
            LOG.info("getOrCreateShortURL - shortID {}", url.getShortID());
            return prefix + env.getProperty("server.port") + "/" + url.getShortID();
        } else {
            String shortCode = generateShortCode();
            url = new URL();
            url.setLongUrl(longUrl);
            url.setShortID(shortCode);
            urlRepository.save(url);
            return prefix + env.getProperty("server.port") + "/" + shortCode;
        }
    }

    private String generateShortCode() {
        final String SALT = "my-salt-string";
        final int MIN_LENGTH = 8;
        Hashids hashids = new Hashids(SALT, MIN_LENGTH);
        long now = System.currentTimeMillis();
        return hashids.encode(now);
    }

    //@Transactional
    public boolean deleteURLById(Integer id) {
        Optional<URL> optionalURL = urlRepository.findById(id);

        if (optionalURL.isPresent()) {
            URL deletedURL = optionalURL.get();
            urlRepository.deleteById(id);
            updateIdWithAscendingSequence();
            return true;
        }

        return false;
    }

    public boolean deleteURLByShortUrl(String shortUrl) {
        String shortID = extractShortIDFromURL(shortUrl);

        URL url = urlRepository.findByShortID(shortID);

        if (url != null) {
            urlRepository.delete(url);
            updateIdWithAscendingSequence();
            return true;
        } else {
            return false;
        }
    }

    private void updateIdWithAscendingSequence() {
        Iterable<URL> urls = urlRepository.findAll();
        int id = 1;

        for (URL url : urls) {
            url.setId(id++);
            urlRepository.save(url);
        }
    }

    private String extractShortIDFromURL(String shortURL) {
        // Assuming the shortID is at the end of the shortURL
        String[] parts = shortURL.split("/");
        return parts[parts.length - 1];
    }
}

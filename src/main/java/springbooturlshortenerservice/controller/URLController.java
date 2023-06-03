package springbooturlshortenerservice.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springbooturlshortenerservice.dao.URL;
import springbooturlshortenerservice.dao.URLRepository;
import springbooturlshortenerservice.service.URLService;

import static springbooturlshortenerservice.service.URLService.extractShortIDFromURL;

@RestController
public class URLController {

    private static final Logger LOG = LoggerFactory.getLogger(URLController.class);
    private final URLService urlService;
    private final URLRepository urlRepository;

    @Autowired
    public URLController(URLService urlService, URLRepository urlRepository) {
        this.urlService = urlService;
        this.urlRepository = urlRepository;
    }


    @PostMapping("/shorten")
    public String shortenURL(@RequestBody Map<String, String> objectMap) {
        String longURL = objectMap.get("longURL");
        LOG.info("shorten request input: {}", longURL);

        return urlService.getOrCreateShortURL(longURL);
    }

    @GetMapping("/{shortURL}")
    public void redirectToLongURL(HttpServletRequest request, HttpServletResponse response, @PathVariable("shortURL") String shortURL) throws IOException, ServletException {
        String shortID = extractShortIDFromURL(shortURL);
        LOG.info("GET request shortID: {}", shortID);
        URL url = urlRepository.findByShortID(shortID);

        if (url != null) {
            LOG.info("Redirecting to longURL: {}", url.getLongUrl());
            response.sendRedirect(url.getLongUrl());
        } else {
            // ShortURL not found in the database
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "There is no such shortURL in the database.");
        }
    }

    @DeleteMapping("/{shortUrl}")
    public ResponseEntity<String> deleteURLByShortUrl(@PathVariable("shortUrl") String shortUrl) {
        boolean deleted = urlService.deleteURLByShortUrl(shortUrl);
        LOG.info("deleted result {}", deleted);
        if (deleted) {
            LOG.info("deleted result {}", true);
            return ResponseEntity.ok("URL successfully deleted");
        } else {
            LOG.info("deleted result {}", false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("URL not found");
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteURLById(@PathVariable("id") Integer id) {
        boolean deleted = urlService.deleteURLById(id);
        LOG.info("deleted result {}", deleted);
        if (deleted) {
            LOG.info("deleted result {}", true);
            return ResponseEntity.ok("URL successfully deleted");
        } else {
            LOG.info("deleted result {}", false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("URL not found");
        }
    }
}

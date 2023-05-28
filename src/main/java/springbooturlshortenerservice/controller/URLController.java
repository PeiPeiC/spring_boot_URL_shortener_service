package springbooturlshortenerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springbooturlshortenerservice.service.URLService;

@RestController
public class URLController {

    private final URLService urlService;

    @Autowired
    public URLController(URLService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public String shortenURL(@RequestBody String longURL) {
        String shortURL = urlService.getOrCreateShortURL(longURL);
        return shortURL;
    }
}




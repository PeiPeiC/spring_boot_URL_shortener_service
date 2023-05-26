package springbooturlshortenerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springbooturlshortenerservice.dao.LongUrlRepository;
//import springbooturlshortenerservice.service.LongUrlService;

@RestController
public class LongUrlController {

    @Autowired
    private LongUrlRepository longUrlRepository;

    //public LongUrlController(LongUrlService longUrlService) {
    //}

    @PostMapping("/urls")
    public String createLongUrl(@RequestBody LongUrl longUrl) {
        longUrlRepository.save(longUrl);
        return "create longURL in database";
    }
}



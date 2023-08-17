package springbooturlshortenerservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import springbooturlshortenerservice.dao.URL;
import springbooturlshortenerservice.dao.URLRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class URLServiceTest {

    private static final String host = "http://localhost:8080";
    @InjectMocks
    private URLService undertest;

    @Mock
    private URLRepository urlRepository;
    @Mock
    private Environment env;

    @Test
    void testShortURLExisted() {
        URL url = new URL();
        url.setLongUrl("longUrl");
        url.setShortID("shortId");

        when(urlRepository.findByLongUrl(anyString())).thenReturn(url);
        when(env.getProperty(anyString())).thenReturn("8080");

        String actual = undertest.getOrCreateShortURL("longUrl");
        String expected = host + "/" + url.getShortID();

        assertEquals(expected, actual);
    }

    @Test
    void deleteURLById() {
    }

    @Test
    void deleteURLByShortUrl() {
    }

    @Test
    void extractShortIDFromURL() {
    }
}

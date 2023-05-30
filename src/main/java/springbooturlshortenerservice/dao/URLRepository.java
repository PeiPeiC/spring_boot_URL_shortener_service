package springbooturlshortenerservice.dao;

import org.springframework.data.repository.CrudRepository;

public interface URLRepository extends CrudRepository<URL, Integer> {
    URL findByShortID(String shortID);


    URL findByLongUrl(String longUrl);
}



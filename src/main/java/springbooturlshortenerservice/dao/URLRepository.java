package springbooturlshortenerservice.dao;

import org.springframework.data.repository.CrudRepository;

public interface URLRepository extends CrudRepository<URL, Integer> {
    URL findByLongUrl(String longUrl);
}



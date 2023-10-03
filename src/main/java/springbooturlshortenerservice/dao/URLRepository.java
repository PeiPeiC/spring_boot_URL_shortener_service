package springbooturlshortenerservice.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends CrudRepository<URL, Integer> {

    void deleteById(Integer id);

    URL findByShortID(String shortID);

    URL findByLongUrl(String longUrl);
}



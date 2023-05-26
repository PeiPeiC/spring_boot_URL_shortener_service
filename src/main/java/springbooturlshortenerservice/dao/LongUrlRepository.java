package springbooturlshortenerservice.dao;

import org.springframework.data.repository.CrudRepository;
import springbooturlshortenerservice.controller.LongUrl;

public interface LongUrlRepository extends CrudRepository<LongUrl, String> {
}



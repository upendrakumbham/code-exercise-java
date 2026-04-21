package com.tpximpact.urlshortener.repository;

import com.tpximpact.urlshortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {

    boolean existsByAlias(String alias);
}

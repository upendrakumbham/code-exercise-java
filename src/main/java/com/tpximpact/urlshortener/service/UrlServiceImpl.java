package com.tpximpact.urlshortener.service;

import com.tpximpact.urlshortener.dto.UrlRequest;
import com.tpximpact.urlshortener.dto.UrlResponse;
import com.tpximpact.urlshortener.entity.Url;
import com.tpximpact.urlshortener.exception.BadRequestException;
import com.tpximpact.urlshortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private static final String BASE_URL = "http://localhost:8080/";
    private final UrlRepository urlRepository;

    @Override
    public UrlResponse shorten(UrlRequest request) {

        String alias = request.customAlias() != null
                ? request.customAlias() : generateAlias();

        if (urlRepository.existsByAlias(alias)) {
            throw new BadRequestException("Url already exists");
        }

        Url url = Url
                .builder()
                .fullUrl(request.fullUrl())
                .alias(alias)
                .build();

        urlRepository.save(url);

        return new UrlResponse(BASE_URL + alias);
    }

    private String generateAlias() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
}

package com.tpximpact.urlshortener.service;

import com.tpximpact.urlshortener.dto.UrlListResponse;
import com.tpximpact.urlshortener.dto.UrlRequest;
import com.tpximpact.urlshortener.dto.UrlResponse;
import com.tpximpact.urlshortener.entity.Url;
import com.tpximpact.urlshortener.exception.BadRequestException;
import com.tpximpact.urlshortener.exception.NotFoundException;
import com.tpximpact.urlshortener.repository.UrlRepository;
import com.tpximpact.urlshortener.util.UrlUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    @Override
    public UrlResponse shorten(UrlRequest request) {

        String alias = request.customAlias() != null
                ? request.customAlias() : UrlUtils.generateAlias();

        if (urlRepository.existsByAlias(alias)) {
            throw new BadRequestException("Url already exists");
        }

        Url url = Url
                .builder()
                .fullUrl(request.fullUrl())
                .alias(alias)
                .build();

        urlRepository.save(url);

        return new UrlResponse(UrlUtils.getBaseUrl(url.getFullUrl()) + alias);
    }

    @Override
    public String getFullUrl(String alias) {
        log.info("Getting full url for alias ' {} ' from the DB", alias);
        return urlRepository.findByAlias(alias)
                .map(Url::getFullUrl)
                .orElseThrow(() -> new NotFoundException("Alias not found"));
    }

    @Override
    public List<UrlListResponse> getAll() {
        return urlRepository.findAll().stream()
                .map(url -> new UrlListResponse(
                        url.getAlias(),
                        url.getFullUrl(),
                        UrlUtils.getBaseUrl(url.getFullUrl()) + url.getAlias()
                ))
                .toList();
    }
}

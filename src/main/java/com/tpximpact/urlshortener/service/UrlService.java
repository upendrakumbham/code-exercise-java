package com.tpximpact.urlshortener.service;

import com.tpximpact.urlshortener.dto.UrlRequest;
import com.tpximpact.urlshortener.dto.UrlResponse;

public interface UrlService {

    UrlResponse shorten(UrlRequest request);

    String getFullUrl(String alias);
}

package com.tpximpact.urlshortener.util;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.UUID;

@Slf4j
public class UrlUtils {

    public static String getBaseUrl(String fullUrl) {
        URI uri = URI.create(fullUrl);
        String scheme = uri.getScheme();        // http / https
        String host = uri.getHost();            // example.com
        int port = uri.getPort();               // -1 if default

        if (port == -1) {
            return scheme + "://" + host + "/";
        }
        log.info("baseUrl: {}", scheme + "://" + host + ":" + port + "/");
        return scheme + "://" + host + ":" + port + "/";
    }

    public static String generateAlias() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
}

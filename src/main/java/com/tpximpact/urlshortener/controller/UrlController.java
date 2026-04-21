package com.tpximpact.urlshortener.controller;

import com.tpximpact.urlshortener.dto.UrlRequest;
import com.tpximpact.urlshortener.dto.UrlResponse;
import com.tpximpact.urlshortener.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequiredArgsConstructor
class UrlController {
    private final UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shorten(@RequestBody @Valid UrlRequest request) {
        UrlResponse response = urlService.shorten(request);
        return ResponseEntity.status(201).body(response);
    }
}

package com.fazekas.coding_challenge.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DefaultWebClient {

    public WebClient getWebClient(String uri) {
        return WebClient.builder().baseUrl(uri).build();
    }
}
